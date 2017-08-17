package pl.hypeapp.presentation.tvshowinfo

import pl.hypeapp.domain.model.TvShowModel
import pl.hypeapp.presentation.base.Presenter
import javax.inject.Inject

class TvShowInfoPresenter @Inject constructor() : Presenter<TvShowInfoView>() {

    private var model: TvShowModel? = null
        get() = view?.getModel()

    override fun onAttachView(view: TvShowInfoView) {
        super.onAttachView(view)
        fillInfoAboutTvShow(model)
    }

    fun onOfficialSitePressed() {
        view?.openBrowserIntent(model?.officialSite)
    }

    fun onImdbSitePressed() {
        view?.openBrowserIntent("${URL_IMDB}${model?.imdbId}")
    }

    fun onTvMazeSitePressed() {
        view?.openBrowserIntent("${URL_TV_MAZE}${model?.id}")
    }

    private fun fillInfoAboutTvShow(model: TvShowModel?) {
        model?.let {
            view?.setSummary(it.summary)
            view?.setGenre(it.genre)
            view?.setEpisodeRuntime(it.episodeRuntime)
            it.network?.let { view?.setNetwork(it) }
            it.officialSite?.let { view?.setOfficialSiteVisible() }
        }
    }

    companion object {
        val URL_TV_MAZE = "http://www.tvmaze.com/shows/"
        val URL_IMDB = "http://www.imdb.com/title/"
    }

}
