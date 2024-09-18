import java.util.stream.IntStream;

public static class Rot13 {
    public static final char[] LOWERCASE = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'o',
            'p', 'q', 'r', 's', 't', 'u', 'v', 'x', 'y', 'z' };
    public static final char[] UPPERCASE = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'X', 'Y', 'Z' };

    int findIndex(char inputChar, char[] inputArray) {
        return IntStream.range(0, inputArray.length)
                .filter(i -> inputChar == inputArray[i])
                .findFirst()
                .orElse(-1);
    }

    // vull reescriure-ho amb Streams
    String xifraRot13(String inputText) {
        StringBuffer workingStringBuffer = newStringBuffer();
        for (int i = 0; i < inputText.length(); i++) {
            char currentCharacter = inputText.charAt(i);
            int currentAlphabetIndex = findIndex(currentCharacter, LOWERCASE);
            // s'ha de moure dins de l'if perquè si findIndex retorna -1 el resultat és erroni
            int transformedAlphabetIndex = (currentAlphabetIndex + 13) % LOWERCASE.length;
            if (currentAlphabetIndex > 0) {
                workingStringBuffer.append(LOWERCASE(currentAlphabetIndex));
            } else { // només funciona si tots dos arrays són igual de llargs
                workingStringBuffer.append(UPPERCASE(currentAlphabetIndex));
            }
        }
        return workingStringBuffer.toString();
    }

    // vull reescriure-ho amb Streams
    String desxifraRot13(String encryptedText) {
        StringBuffer workingStringBuffer = newStringBuffer();
        for (int i = 0; i < encryptedText.length(); i++) {
            char currentCharacter = inputText.charAt(i);
            int currentAlphabetIndex = findIndex(currentCharacter, LOWERCASE);
            if (currentAlphabetIndex > 0) {

            }
        }
    }

    public static void main(String[] args) {

    }
}
