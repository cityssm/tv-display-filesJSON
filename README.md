# TV Displays - files.json Generator

This project is a Java application that can live inside a folder of files.
When run, the application creates or updates a `files.json` file in the directory.

The `files.json` file is compatible with several content types
in the [tv-display](https://github.com/cityssm/tv-display) project.

The idea is that a completely disconnected system could be updated with a USB key.
The user could copy over the files, run this application inside the folder, then refresh the browser.

Right now, the generated json contains a filtered list of images in a property called "backgroundImages".

## Usage

If the JAR file is inside the same folder as the content:

    > java -jar FilesJsonGenerator.jar

If the JAR file is in a different folder:

    > java -jar FilesJsonGenerator.jar path/to/folder
