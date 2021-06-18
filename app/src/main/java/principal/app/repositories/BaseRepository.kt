package principal.app.repositories

import principal.app.services.RetrofitService

abstract class BaseRepository {

    protected var retrofitService: RetrofitService = RetrofitService()

}