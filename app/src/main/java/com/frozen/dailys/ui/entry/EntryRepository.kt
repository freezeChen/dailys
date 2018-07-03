package com.frozen.dailys.ui.entry

import com.frozen.dailys.component.AppData
import com.frozen.dailys.model.Response
import com.frozen.dailys.model.User
import com.frozen.dailys.util.extensions.md5
import io.reactivex.Observable

class EntryRepository {
    fun login(username: String, password: String): Observable<Response<User>> {
        return AppData.login(username, password.md5())
    }
}