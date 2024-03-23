package com.kodnest.service;

import java.util.List;

import com.kodnest.entity.Song;

public interface SongService {
	
	
	public String addsong(Song song);
	public boolean songExists(String name) ;
	public List<Song> fetchAllSong();
	public void updateSong(Song s);

}
