// This is a structural pattern.
// The idea is to hide the complexities of the system and provides an interface to the client.
// This pattern invovles a single class which provides simplified method required by client
// The interface can be over a complex libary, framework or ...


// ==============================
// Some complex components of video compression system

import java.io.File;

class VideoFile {
    private String name;
    private String codecType;

    public VideoFile(String name) {
        this.name = name;
        this.codecType = name.substring(name.indexOf(".") + 1);
    }

    public String getCodecType() { return codecType; }
    public String getName() { return name; }
}

interface Codec {}

class MPEG4CompressionCodec implements Codec {
    public String type = "mp4";
}

class OggCompressionCodec implements Codec {
    public String type = "ogg";
}

class CodecFactory {
    public static Codec extract(VideoFile file) {
        String type = file.getCodecType();
        if (type.equals("mp4")) {
            return new MPEG4CompressionCodec();
        } else {
            return new OggCompressionCodec();
        }
    }
}

class BitrateReader {
    public static VideoFile read(VideoFile file, Codec codec) {
        System.out.println("Reading file...");
        return file;
    }

    public static VideoFile convert(VideoFile buffer, Codec codec) {
        System.out.println("Writing file...");
        return buffer;
    }
}

class AudioMixer {
    public File fix(VideoFile result) {
        System.out.println("Fixing audio...");
        return new File("tmp");
    }
}

// ==============================
// The facade class

public class VideoConversionFacade {
    public File convertVideo(String fileName, String format) {
        System.out.println("VideoConversionFacade: conversion started.");
        VideoFile file = new VideoFile(fileName);
        Codec sourceCodec = CodecFactory.extract(file);
        Codec destinationCodec;
        if (format.equals("mp4")) {
            destinationCodec = new OggCompressionCodec();
        } else {
            destinationCodec = new MPEG4CompressionCodec();
        }
        VideoFile buffer = BitrateReader.read(file, sourceCodec);
        VideoFile intermediateResult = BitrateReader.convert(buffer, destinationCodec);
        File result = (new AudioMixer()).fix(intermediateResult);
        System.out.println("VideoConversionFacade: conversion completed.");
        return result;
    }
}

public class Facade {
    public static void main(String[] args) {
        VideoConversionFacade converter = new VideoConversionFacade();
        File mp4Video = converter.convertVideo("youtubevideo.ogg", "mp4");
        // ...
    }
}

