package com.example.mynotes.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mynotes.R
import com.example.mynotes.model.`object`.Notes
import com.example.mynotes.viewmodel.NotesViewModel
import kotlinx.android.synthetic.main.activity_add_notes.*

class AddNotes : AppCompatActivity() {
    private lateinit var notesViewModel: NotesViewModel
    private var notes: Notes? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)

        supportActionBar?.title = resources.getString(R.string.add_notes)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        notesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)

        notes = intent.getParcelableExtra("Notes") as? Notes

        //When Save button is clicked
        if (notes == null) {
            save_btn.setOnClickListener {
                if (input_title.text.toString().isNotEmpty() && input_description.text.toString()
                        .isNotEmpty()
                ) {
                    notesViewModel.insertNotes(
                        Notes(
                            title = input_title.text.toString(),
                            desc = input_description.text.toString()
                        )
                    )

                    Toast.makeText(
                        applicationContext,
                        "Data is successfully added",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Fill the form completely",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                goToHome()
            }
        } else {
            input_title.setText(notes?.title)
            input_description.setText(notes?.desc)

            save_btn.setOnClickListener {
                if (input_title.text.toString().isNotEmpty() && input_description.text.toString()
                        .isNotEmpty()
                ) {
                    notes?.title = input_title.text.toString()
                    notes?.desc = input_description.text.toString()

                    notesViewModel.updateNotes(notes!!)

                    Toast.makeText(
                        applicationContext,
                        "Data is successfully updated",
                        Toast.LENGTH_SHORT
                    ).show()

                    Log.d("title", input_title.text.toString())
                    Log.d("desc", input_description.text.toString())
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Fill the form completely",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                notes = null

                goToHome()
            }
        }
    }

    private fun goToHome() {
        val homeIntent = Intent(applicationContext, MainActivity::class.java)
        startActivity(homeIntent)
    }
}
