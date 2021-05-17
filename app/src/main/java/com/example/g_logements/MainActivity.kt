package com.example.g_logements

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Surface
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.example.g_logements.models.Logement
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_list_logements.view.*


class MainActivity : AppCompatActivity() {

    lateinit var listLogements : ListView
    lateinit var database: FirebaseDatabase
    lateinit var myRef: DatabaseReference
    private lateinit var lesLogements: ArrayList<Logement>
    private lateinit var adapter: ArrayAdapter<Logement>
    lateinit var logo:ImageView
    lateinit var prix:TextView
    lateinit var surface: TextView
    lateinit var typelogement:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listLogements = findViewById(R.id.lv_product)
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("logements")
        lesLogements = ArrayList()

        logo = findViewById(R.id.logo)
        prix = findViewById(R.id.row_prix)
        surface = findViewById(R.id.row_surface)
        typelogement = findViewById(R.id.row_type_logement)

        myRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                lesLogements.clear()
                for (postSnapshot in snapshot.children) {
                    //getting artist
                    val  td : Map<String, Object> = postSnapshot.getValue() as HashMap<String, Object>
                    val p = Logement(td.get("type_log").toString(),td.get("surface").toString().toFloat(),td.get("prix").toString().toFloat(),td.get("address").toString(),
                    td.get("nbre_piece").toString().toInt(),td.get("image").toString().toInt())
                    lesLogements.add(p)
                }
                val adapter = customAdapter(applicationContext,lesLogements)
                listLogements.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

    class customAdapter(var monContext: Context,var logts:List<Logement>): ArrayAdapter<Logement>(monContext,0,logts){
        override fun getView(position: Int, view: View?, parent: ViewGroup): View {
            val layoutt = LayoutInflater.from(monContext).inflate(R.layout.activity_list_logements,null)
            layoutt.logo.setImageResource(logts[position].image)
            layoutt.row_prix.text = logts[position].prix.toString()
            layoutt.row_surface.text = logts[position].surface.toString()
            layoutt.row_type_logement.text = logts[position].type_log
            return layoutt
        }
    }
}