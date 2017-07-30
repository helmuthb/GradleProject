package at.breitenfellner.jokes;


import java.io.Serializable;
import java.util.Random;

public class Jokes {
    Joke[] jokes = {
            new Joke("Some people just have a way with words, and other people … oh … not have way.",
                    "Steve Martin"),
            new Joke("The four most beautiful words in our common language: I told you so.",
                    "Core Vidal"),
            new Joke("All pro athletes are bilingual. They speak English and profanity.",
                    "Gordie Howe"),
            new Joke("I don’t want to brag, but I do speak pig Latin; I mean, I’m not fluent, but I’m sure if I ever went there, I could get by.",
                    "Bonnie McFarlane"),
            new Joke("If you understand English, press 1. If you do not understand English, press 2.",
                    "Recording on an Australian tax help line")
    };
    Random random;

    public Jokes () {
        random = new Random();
    }
    public Joke getJoke() {
        int nr = random.nextInt(jokes.length);
        return jokes[nr];
    }

    public static class Joke implements Serializable {
        public final String text;
        public final String author;

        public Joke(String text, String author) {
            this.text = text;
            this.author = author;
        }
    }
}
