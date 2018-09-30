package com.zetagh.clanbattleapp.viewcontrollers.activities

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.zetagh.clanbattleapp.R
import com.zetagh.clanbattleapp.models.Publication
import com.zetagh.clanbattleapp.networking.ClanBattlesApi.Companion.getPublicationByGamer
import kotlinx.android.synthetic.main.activity_add_publication.*
import kotlinx.android.synthetic.main.content_add_publication.*
import kotlinx.android.synthetic.main.content_add_publication.view.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.net.URL
import java.util.*

class AddPublicationActivity : AppCompatActivity() {

    var downloadUri :String?=null
    private val PICK_IMAGE_REQUEST = 1234
    private var filePath:Uri?=null
    internal var storage : FirebaseStorage?=null
    internal var storageReference: StorageReference?=null

    lateinit var titleJson : String
    lateinit var descriptionJson : String
    var publicationJson:Publication?=null

    var id:Int?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_publication)
        setSupportActionBar(toolbar)

        var intentExtras = intent
        id = intentExtras.getIntExtra("id",1)
        //Load image
        storage = FirebaseStorage.getInstance()
        storageReference = storage!!.reference
        buttonListenerToGallery()
        addPublicationBottonOnClick()
        postButton()


        //Networking POST

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == PICK_IMAGE_REQUEST &&  resultCode == RESULT_OK && data!= null && data.data !=null){
            filePath = data!!.data
            try {
//                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver,filePath)
//                loadFromGalleryButton.setImageBitmap(bitmap)
                loadFromGalleryButton.setImageURI(filePath)
                loadFromGalleryButton.visibility = View.VISIBLE
            }catch (e:IOException){
                e.printStackTrace()
            }
        }

    }

    private fun buttonListenerToGallery() {
        chooseImage.setOnClickListener{
            var i = Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            i.type = "image/*"
            i.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(i,"SELECT PICTURE"),PICK_IMAGE_REQUEST)
        }
    }

    private fun uploadImage(){
        if(filePath!=null){

            val progressDialog = ProgressDialog(this)
            progressDialog.setTitle("Uploading...")
            progressDialog.show()

            val ref = storageReference!!.child("images/"+ UUID.randomUUID().toString())

            val uploadTask = ref.putFile(filePath!!)
                    .addOnSuccessListener {
                        progressDialog.dismiss()
                        Toast.makeText(applicationContext,"Uploaded",Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener { exception ->
                        Log.d("UploadImage",exception.message)
                        Toast.makeText(applicationContext,"Failed",Toast.LENGTH_SHORT).show()
                    }
                    .addOnProgressListener { taskSnapshot ->
                        val progress = 100.0 * taskSnapshot.bytesTransferred/taskSnapshot.totalByteCount
                        progressDialog.setMessage("Uploaded "+ progress.toInt() +"%...")
                    }
            val urlTask = uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> {
                if(!it.isSuccessful){
                    it.exception?.let {
                        throw it
                    }
                }
                return@Continuation ref.downloadUrl
            }).addOnCompleteListener{
                if(it.isSuccessful){
                    downloadUri = it.result.toString()
                }
            }
        }
    }

    private fun addPublicationBottonOnClick(){
        addPublicationButton.setOnClickListener {
            uploadImage()
        }
    }

    private fun initPublication():JSONObject{
        titleJson = titleEditView.text.toString()
        descriptionJson = descriptionEditText.text.toString()
//        publicationJson.title = titleJson
//        publicationJson.description= descriptionJson
//        publicationJson.urlToImage = downloadUri

        val title= "Titulo insertado para pruebas"
        val description= "Descripcion insertada para pruebas"
        val urlToImage = "http://m.memegen.com/jq0ry1.jpg"

        var jsonObject = JSONObject()
        jsonObject.put("id",66)
        jsonObject.put("title",title)
        jsonObject.put("description",description)
        jsonObject.put("urlToImage",urlToImage)
        jsonObject.put("publicationDate","2018-09-29T16:04:20.4070326-07:00")
        jsonObject.put("status","ACT")
        jsonObject.put("gamerId",2)
        jsonObject.put("gameId",2)



        return jsonObject
    }
    private fun postPublication(){
        //Inicialization of the object created by user
        val publicationObject = initPublication()
//        AndroidNetworking.post(getPublicationByGamer(id!!))
//        AndroidNetworking.post(getPublicationByGamer(1))
        val url = "http://clanbattles.somee.com/clanbattles/v1/gamers/2/publications"
        AndroidNetworking.post(url)
                .addJSONObjectBody(publicationObject)
                .setTag("Nose")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONArray(object : JSONArrayRequestListener{
                    override fun onResponse(response: JSONArray?) {
                        Toast.makeText(applicationContext,"Funko",Toast.LENGTH_SHORT)
                        Log.d("postPublication",response.toString())
                        System.out.println("AQUI SE DA EL SUCCESS")
                    }

                    override fun onError(anError: ANError?) {
                        Toast.makeText(applicationContext,"Errorr",Toast.LENGTH_SHORT)
                        Log.d("postPublication",anError.toString())
                        System.out.println("AQUI SE DA EL ERROR")
                        System.out.println(publicationObject)

                    }

                })
    }

    private fun postButton(){
        postPublication.setOnClickListener {
            Toast.makeText(applicationContext,"Button Pressed",Toast.LENGTH_SHORT)
            postPublication()
        }
    }
}

