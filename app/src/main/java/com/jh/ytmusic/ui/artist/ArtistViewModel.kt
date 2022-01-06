package com.jh.ytmusic.ui.artist

import com.jh.ytmusic.base.BaseViewModel
import com.jh.ytmusic.model.AlbumModel
import com.jh.ytmusic.model.SongModel

class ArtistViewModel: BaseViewModel() {

    companion object {
        private const val ARTIST = "지코(ZICO)"
    }

    val popularSongList = listOf<SongModel>(
        SongModel("아무노래","지코(ZICO)","https://image.bugsm.co.kr/album/images/1000/203001/20300127.jpg"),
        SongModel("Okay Dokey","송민호(MINO), 지코(ZICO)","https://image.bugsm.co.kr/album/images/1000/5285/528581.jpg"),
        SongModel("BERMUDA TRIANGLE(feat. Crush, DEAN)","ZICO","https://image.bugsm.co.kr/album/images/1000/200686/20068683.jpg"),
        SongModel("Boys and Girls(feat. Babylon)","ZICO","https://image.bugsm.co.kr/album/images/1000/200117/20011786.jpg"),
        SongModel("I Am, You, You Are Me","ZICO","https://image.bugsm.co.kr/album/images/1000/200188/20018879.jpg")
    )

    val albumList = listOf<AlbumModel>(
        AlbumModel("RANDOM BOX", "EP·2020","https://image.bugsm.co.kr/album/images/1000/203350/20335052.jpg"),
        AlbumModel("THINKING, Part 2", "EP·2019","https://image.bugsm.co.kr/album/images/1000/9482/948273.jpg"),
        AlbumModel("THINKING, Part 1", "EP·2019","https://image.bugsm.co.kr/album/images/1000/202797/20279704.jpg"),
        AlbumModel("TELEVISION", "EP·2017","https://image.bugsm.co.kr/album/images/1000/201077/20107734.jpg"),
        )
}