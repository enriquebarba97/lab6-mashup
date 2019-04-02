package aiss.model.resources;

import java.util.Arrays;
import java.util.Collection;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import aiss.model.Song;

public class SongResource {

	private String uri = "http://playlist-api.appspot.com/api/songs";
	//private String uri = "http://localhost:8095/api/songs";

	
	public Collection<Song> getAll() {
		ClientResource cr = null;
		Song [] songs = null;
		try {
			cr = new ClientResource(uri);
			songs = cr.get(Song[].class);
			
		} catch (ResourceException re) {
			System.err.println("Error when retrieving all songs: " + cr.getResponse().getStatus());
			throw re;
		}
		
		return Arrays.asList(songs);
	}
	

	public Song getSong(String songId) {
		//TODO
		ClientResource cr = null;
		Song result = null;
		String uriFinal = uri + "/" + songId;
		try {
			cr = new ClientResource(uriFinal);
			result = cr.get(Song.class);
		} catch(ResourceException re) {
			System.err.println("Error when retrieving a song: " + cr.getResponse().getStatus());
			throw re;
		}
		
		return result;
	}
	

	public Song addSong(Song song) {
		// TODO
		ClientResource cr = null;
		Song result = song;
		try {
			cr = new ClientResource(uri);
			result = cr.post(result, Song.class);
		} catch(ResourceException re) {
			System.err.println("Error when adding a song: " + cr.getResponse().getStatus());
			throw re;
		}
		
		return result;

	}
	
	public boolean updateSong(Song song) {
		// TODO
		ClientResource cr = null;
		Boolean success = true;
		try {
			cr = new ClientResource(uri);
			cr.setEntityBuffering(true);
			cr.put(song);
		} catch(ResourceException re) {
			System.err.println("Error when updating a song: " + cr.getResponse().getStatus());
			success = false;
		}
		
		return success;
	}
	

	public boolean deleteSong(String songId) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource(uri + "/" + songId);
			cr.setEntityBuffering(true);		// Needed for using RESTlet from JUnit tests
			cr.delete();
			
		} catch (ResourceException re) {
			System.err.println("Error when deleting the song: " + cr.getResponse().getStatus());
			success = false;
			throw re;
		}
		
		return success;
		
	}
}
