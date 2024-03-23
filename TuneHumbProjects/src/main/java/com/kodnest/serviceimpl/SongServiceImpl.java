package com.kodnest.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.entity.Song;
import com.kodnest.repository.SongRepository;
import com.kodnest.service.SongService;
@Service
public class SongServiceImpl implements SongService {
	@Autowired
	SongRepository songRepository;

	@Override
	public String addsong(Song song) {
		songRepository.save(song);
		return "song add..";
		
	}

	@Override
	public boolean songExists(String name) {
	 	Song song=songRepository.findByName(name);
		if(song==null) {
			return false;
		}
		else {
		return true;
		}
	}

	@Override
	public List<Song> fetchAllSong() {
		List<Song> song = songRepository.findAll();
		return song;
	}

	@Override
	public void updateSong(Song s) {
		songRepository.save(s);
		
	}

	

}
