package com.burakeregar.githubsearch.api

import com.burakeregar.githubsearch.home.model.RepoResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Burak Eregar on 23.05.2017.
 * burakeregar@gmail.com
 * https://github.com/burakeregar
 */
interface Endpoints {
    @GET("/search/repositories?sort=stars&order=desc")
    fun search(@Query("q") key: String): Observable<RepoResponse>
}