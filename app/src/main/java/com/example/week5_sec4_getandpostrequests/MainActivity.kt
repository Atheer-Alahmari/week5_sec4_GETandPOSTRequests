package com.example.week5_sec4_getandpostrequests

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val apiInterface by lazy { APIClient().getClient()?.create(APIInterface::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_save.setOnClickListener {
            if (nameED.text.isNotEmpty()) {
                postName()
                nameED.text.clear()
            } else {
                Toast.makeText(this, " is empty", Toast.LENGTH_LONG).show()
            }
        }
        btn_show.setOnClickListener {
            getName()
        }

    }

private fun getName(){

        apiInterface?.getName()?.enqueue(object: Callback<ArrayList<AddNameItem?>?> {
            override fun onResponse(
                call: Call<ArrayList<AddNameItem?>?>,
                response: Response<ArrayList<AddNameItem?>?>
            ) {

                var displayResponse = ""

                val datumList = response.body()!!

                for (datum in datumList!!) {
                    displayResponse += "${datum?.name} \n"

                }

                textView2.text=displayResponse
              //  textView2.text = displayResponse

            }

            override fun onFailure(call: Call<ArrayList<AddNameItem?>?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Unable to get data", Toast.LENGTH_LONG).show()
            }
        })

}

    //--------------------------------------add-----------------------
    private fun postName(){
        apiInterface?.postName(AddNameItem(nameED.text.toString()))?.enqueue(object:
            Callback<AddNameItem> {
            override fun onResponse(
                call: Call<AddNameItem>,
                response: Response<AddNameItem>
            ) {
                Toast.makeText(applicationContext, "adding Sucess!", Toast.LENGTH_SHORT).show();
            }

            override fun onFailure(call: Call<AddNameItem>, t: Throwable) {
                Toast.makeText(applicationContext, "Unable to get data", Toast.LENGTH_LONG).show()
            } })
    }
}