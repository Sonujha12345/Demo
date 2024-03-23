package com.kodnest.service;

import java.util.List;

import com.kodnest.entity.Playlist;

public interface PlaylistService {
	
	public void addplaylist(Playlist playlist);
	public List<Playlist> fetchAllSong();


}