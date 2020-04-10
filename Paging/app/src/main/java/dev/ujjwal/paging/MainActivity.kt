package dev.ujjwal.paging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = GitRepoAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)

        val itemViewModel = ViewModelProviders.of(this).get(GitRepoViewModel::class.java)

        itemViewModel.gitRepoPagedList.observe(this, Observer {
            adapter.submitList(it)
        })

        recyclerView.adapter = adapter
    }
}
