package com.example.medico.data.model


import android.annotation.SuppressLint
import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import androidx.work.impl.utils.futures.SettableFuture
import com.example.medico.data.api.ServiceBuilder
import com.example.medico.data.api.ServiceProvider
import com.google.common.util.concurrent.ListenableFuture
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("RestrictedApi")
class SyncService(val ctx: Context, val workParamters: WorkerParameters):ListenableWorker(ctx, workParamters){

    lateinit var  future:SettableFuture<Result>
    val api: ServiceProvider by lazy {
        ServiceBuilder.buildService(ServiceProvider::class.java)
    }


    override fun startWork(): ListenableFuture<Result> {

        future = SettableFuture.create()
        val teams = RoomService.appDataBase.getConseilDao().getNonSynConseils()
        addTeams(teams)
        return future
    }





    fun addTeams(conseils:List<Conseil>) {
        for(conseil in conseils){
            val result = api.addConseil(conseil)
            result.enqueue(object: Callback<ResponseBack> {

                override fun onFailure(call: Call<ResponseBack>?, t: Throwable?) {

                    future.set(Result.retry())


                }

                override fun onResponse(call: Call<ResponseBack>?, response: Response<ResponseBack>?) {

                    if(response?.isSuccessful!!) {
                        for (item in conseils) {
                            item.isSynchronized = 1
                        }
                        RoomService.appDataBase.getConseilDao().updateConseils(conseils)
                        future.set(Result.success())

                    }
                    else
                    {
                        future.set(Result.retry())
                    }
                }

            })
        }


    }


}
