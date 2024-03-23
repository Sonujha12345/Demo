package com.kodnest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kodnest.entity.Playlist;
import com.kodnest.entity.Song;
import com.kodnest.serviceimpl.PlaylistServiceImpl;
import com.kodnest.serviceimpl.SongServiceImpl;

@Controller
public class PlaylistController {
	@Autowired
	SongServiceImpl songServiceImpl;
	@Autowired
	PlaylistServiceImpl playlistServiceImpl;
	
	@GetMapping("/createplaylist")
	public String createPlaylist(Model model) {
		List<Song> songList=songServiceImpl.fetchAllSong();
		model.addAttribute("songs", songList);
		return "createplaylist";
	}
	@PostMapping("/addplaylist")
	public String  addplaylist(@ModelAttribute Playlist playlist ) {
		//updating the playlist table
		playlistServiceImpl.addplaylist(playlist);
		//updating the song table..
		List<Song> songs = playlist.getSongs();
		for (Song s : songs) {
			s.getPlaylists().add(playlist);
			songServiceImpl.updateSong(s);
		}
		return "adminhome";
		
	}
	
	@GetMapping("/viewplaylist")
	public String viewPlaylist(Model model) {
		List<Playlist> PlayList=playlistServiceImpl.fetchAllSong();
		model.addAttribute("playlist",PlayList );
		return "displayplaylist";
	}

}
