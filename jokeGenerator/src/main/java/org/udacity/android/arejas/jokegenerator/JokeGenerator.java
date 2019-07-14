package org.udacity.android.arejas.jokegenerator;

public class JokeGenerator {

    private static String[] jokes = {
            "Today at the bank, an old lady asked me to help check her balance. So I pushed her over.",
            "I bought some shoes from a drug dealer. I don't know what he laced them with, but I've been tripping all day.",
            "I told my girlfriend she drew her eyebrows too high. She seemed surprised.",
            "My dog used to chase people on a bike a lot. It got so bad, finally I had to take his bike away.",
            "I'm so good at sleeping. I can do it with my eyes closed.",
            "My boss told me to have a good day.. so I went home.",
            "Why is Peter Pan always flying? He neverlands.",
            "A woman walks into a library and asked if they had any books about paranoia. The librarian says 'They're right behind you!'",
            "The other day, my wife asked me to pass her lipstick but I accidentally passed her a glue stick. She still isn't talking to me.",
            "Why do blind people hate skydiving? It scares the hell out of their dogs.",
            "When you look really closely, all mirrors look like eyeballs.",
            "My friend says to me: 'What rhymes with orange' I said: 'No it doesn't'",
            "What do you call a guy with a rubber toe? Roberto.",
            "What did the pirate say when he turned 80 years old? Aye matey.",
            "My wife told me I had to stop acting like a flamingo. So I had to put my foot down.",
            "I couldn't figure out why the baseball kept getting larger. Then it hit me.",
            "Why did the old man fall in the well? Because he couldn't see that well.",
            "I ate a clock yesterday, it was very time consuming.",
            "Whatdya call a frenchman wearing sandals? Phillipe Phillope."
    };

    public static String getRandomJoke() {
        return jokes[(int)(Math.random()*jokes.length)];
    }

}
