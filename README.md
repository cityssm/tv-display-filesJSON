# TV Displays - files.json Generator

This project will be a Java application that lives inside a folder of files.
When run, the application will create or update a `files.json` file in the directory.

The `files.json` file will then be compatible with several content types
in the [tv-display](https://github.com/cityssm/tv-display) project.

The idea is that a completely disconnected system could be updated with a USB key.
The user could copy over the files, run the application, then refresh the browser.

Right now, the generated json contains a filtered list of images in a property called "backgroundImages".
