package ca.saultstemarie.tvdisplay;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.Files;
import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class StartFileListGenerator {

	public static void main (String[] args) {
		
		// Get the path as a string
		
		String folderPath = ".";
		
		if (args.length > 0) {
			folderPath = args[0];
		}
		
		System.out.println("Folder Path: " + folderPath);
		
		// Create the File object for the folder
		
		File folder = new File(folderPath);
		
		// Check folder permissions
		
		if (!folder.exists()) {
			System.err.println("The path \"" + folder.getAbsolutePath() + "\" does not exist.");
			System.exit(1);
		}
		
		if (!folder.isDirectory()) {
			System.err.println("The path \"" + folder.getAbsolutePath() + "\" is not a folder.");
			System.exit(1);
		}
		
		if (!folder.canRead()) {
			System.err.println("No read access on the \"" + folder.getAbsolutePath() + "\" folder.");
			System.exit(1);
		}
		
		if (!folder.canWrite()) {
			System.err.println("No write access on the \"" + folder.getAbsolutePath() + "\" folder.");
			System.exit(1);
		}
		
		// Get files.json file
		
		File filesJSON = new File(folder, "files.json");
		
		if (filesJSON.exists()) {
			if (!filesJSON.canWrite()) {
				System.err.println("files.json already exists in \"" + folder.getAbsolutePath() + "\" but is write protected.");
				System.exit(1);
			}
			
			filesJSON.delete();
		}
		
		// Get applicable files from the folder
		
		File[] backgroundImageFiles = folder.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File file) {
				
				if (file.isDirectory())
					return false;
				
				String fileName = file.getName().toLowerCase();
				
				if (fileName.startsWith(".")) {
					return false;
				}
				
				if (fileName.endsWith(".jpg") || fileName.endsWith(".png")) {
					return true;
				}
				
				return false;
			}
		});
		
		Arrays.sort(backgroundImageFiles);
		
		// Create the JSON object
		
		JSONArray backgroundImages = new JSONArray();
		
		for (File backgroundImageFile : backgroundImageFiles) {
			backgroundImages.add(backgroundImageFile.getName());
		}
		
		JSONObject json = new JSONObject();
		json.put("backgroundImages", backgroundImages);
		
		// Write the file
		
		try {
			Files.write(filesJSON.toPath(), json.toJSONString().getBytes());
		}
		catch (Exception e) {
			System.err.println("An error occurred while writing files.json.");
			e.printStackTrace(System.err);
			System.exit(1);
		}
	}
}
