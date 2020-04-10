package dev.ujjwal.paging

import androidx.paging.PageKeyedDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitRepoDataSource : PageKeyedDataSource<Int, GitRepo>() {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, GitRepo>) {
        val gitRepoService = GitRepoServiceBuilder.buildService(GitRepoService::class.java)
        val response = gitRepoService.getRepositories(FIRST_PAGE, PAGE_SIZE, TOPIC)

        response.enqueue(object : Callback<GitRepoResponse> {
            override fun onFailure(call: Call<GitRepoResponse>, t: Throwable) {
            }

            override fun onResponse(call: Call<GitRepoResponse>, response: Response<GitRepoResponse>) {
                if (response.isSuccessful) {
                    val gitRepoResponse = response.body()
                    val gitRepo = gitRepoResponse?.items

                    gitRepo?.let {
                        callback.onResult(gitRepo, null, FIRST_PAGE + 1)
                    }
                }
            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, GitRepo>) {
        val gitRepoService = GitRepoServiceBuilder.buildService(GitRepoService::class.java)
        val response = gitRepoService.getRepositories(FIRST_PAGE, PAGE_SIZE, TOPIC)

        response.enqueue(object : Callback<GitRepoResponse> {
            override fun onFailure(call: Call<GitRepoResponse>, t: Throwable) {
            }

            override fun onResponse(call: Call<GitRepoResponse>, response: Response<GitRepoResponse>) {
                if (response.isSuccessful) {
                    val gitRepoResponse = response.body()
                    val gitRepo = gitRepoResponse?.items

                    val key = if (gitRepoResponse!!.totalCount > params.key) params.key + 1 else 0

                    gitRepo?.let {
                        callback.onResult(gitRepo, key)
                    }
                }
            }
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, GitRepo>) {
        val gitRepoService = GitRepoServiceBuilder.buildService(GitRepoService::class.java)
        val response = gitRepoService.getRepositories(FIRST_PAGE, PAGE_SIZE, TOPIC)

        response.enqueue(object : Callback<GitRepoResponse> {
            override fun onFailure(call: Call<GitRepoResponse>, t: Throwable) {
            }

            override fun onResponse(call: Call<GitRepoResponse>, response: Response<GitRepoResponse>) {
                if (response.isSuccessful) {
                    val gitRepoResponse = response.body()
                    val gitRepo = gitRepoResponse?.items

                    val key = if (params.key > 1) params.key - 1 else 0

                    gitRepo?.let {
                        callback.onResult(gitRepo, key)
                    }
                }
            }
        })
    }

    companion object {
        const val PAGE_SIZE = 10
        const val FIRST_PAGE = 1
        const val TOPIC = "android"
    }
}