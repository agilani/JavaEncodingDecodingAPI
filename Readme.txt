


Overview
------------------------
This API is a robust api.
Expansion: For expansion, i just need to implement Coder.java which is an interface and i can create a concerete class. I need to add another enum to CodingTypes. And thats all
Robustness: User can encode/decode data obtained from a FILE, a URL or a STRING. URL data is parsed using jsoup so only text gets encoded or decoded.
Error Handling: I have made few Custom Exceptions to handle different exceptions

Structure
------------------------
I have packaged everything under ie.gmit.Encoder.
I have made sub folders for each encoding/decoding techniques.

build.xml
------------------------
Due to the layout of my project please have a look through my build.xml as i have changed it a lot.

Dependencies
------------------------
jsoup-1.7.3
junit
they are both in a folder called "libs". they are cruical for the build.xml script





Adeel Gilani
G0027918
www.agilani.me
me@agilani.me
