import org.example.SuggestionEngine;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import java.util.Collections;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SuggestionEngineTest {
    private SuggestionEngine suggestionEngine;

    @Before
    public void setUp() throws Exception {
        suggestionEngine = new SuggestionEngine();
        suggestionEngine.loadDictionaryData(Paths.get("src/resources/words.txt"));
        System.out.println("Dictionary loaded with words: " + suggestionEngine.getWordSuggestionDB().keySet());
    }

    @Test
    public void testKnownWord() {
        String result = suggestionEngine.generateSuggestions("example");
            System.out.println("No suggestions needed.");
        assertEquals("Expected no suggestions for a known word but got some.", "", result);
    }

    @Test
    public void testEmptyInput() {
        String result = suggestionEngine.generateSuggestions("");
        List<String> suggestions = result.isEmpty() ? Collections.emptyList() : Arrays.asList(result.split("\n"));

        System.out.println("Generated suggestions for empty input: " + suggestions); // Debugging output
        Assert.assertTrue("Expected no suggestions for empty input but got some", suggestions.isEmpty());
    }


    @Test
    public void testNoValidSuggestions() {
        String result = suggestionEngine.generateSuggestions("hello");
        System.out.println("Suggestions for 'hello': " + result);
        List<String> suggestions = result.isEmpty() ? Collections.emptyList() : Arrays.asList(result.split("\n"));
        System.out.println("Suggestions list for 'hello': " + suggestions);
        Assert.assertTrue("Expected no suggestions but got some.", suggestions.isEmpty());
    }
}


