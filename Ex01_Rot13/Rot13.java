import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Rot13 {
    public static final char[] LOWERCASE = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'o',
            'p', 'q', 'r', 's', 't', 'u', 'v', 'x', 'y', 'z' };
    public static final char[] UPPERCASE = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'X', 'Y', 'Z' };

    private static int findIndex(char inputChar, char[] inputArray) {
        return IntStream.range(0, inputArray.length)
                .filter(i -> inputChar == inputArray[i])
                .findFirst()
                .orElse(-1); // mètode d'OptionalInt -> o és un int o és un null, i si és null fem això
    }

    // si és minúscula retorna [0, index]
    // si és majúscula retorna [1, index]
    // si no hi és retorna [-1, index]
    private static int[] isInSet(char inputChar) {
        int index = findIndex(inputChar, LOWERCASE);
        if (index >= 0) {
            return new int[] { 0, index };
        }
        index = findIndex(inputChar, UPPERCASE);
        if (index >= 0) {
            return new int[] { 1, index };
        }
        return new int[] { -1, index };
    }

    private static String xifraRot13(String inputText) {
        return inputText.chars() // Java no permet fer map sobre arrays o strings així que creem un stream d'ints
                .mapToObj(intChar -> (char) intChar)
                .map(character -> {
                    int[] characterData = isInSet(character);
                    if (characterData[0] == 0) {
                        return LOWERCASE[(characterData[1] + 13) % LOWERCASE.length]; // per tal d'evitar consultar un
                                                                                      // índex que estigui fora de rang,
                                                                                      // ens basem en el residu de la
                                                                                      // divisió
                    } else if (characterData[0] == 1) {
                        return UPPERCASE[(characterData[1] + 13) % UPPERCASE.length]; // per tal d'evitar consultar un
                                                                                      // índex que estigui fora de rang,
                                                                                      // ens basem en el residu de la
                                                                                      // divisió
                    } else {
                        return character; // si no és ni a LOWERCASE ni a UPPERCASE el retornem tal qual
                    }
                })
                .map(character -> character.toString()) // convertim cada char en String i els unim amb
                                                        // Collectors.joining()
                .collect(Collectors.joining());
    }

    private static String desxifraRot13(String inputText) {
        return inputText.chars() // Java no permet fer map sobre arrays o strings així que creem un stream d'ints
                .mapToObj(intChar -> (char) intChar)
                .map(character -> {
                    int[] characterData = isInSet(character);
                    if (characterData[0] == 0) {
                        return LOWERCASE[Math.floorMod(characterData[1] - 13, LOWERCASE.length)]; // floorMod per a
                                                                                                  // evitar sortir de
                                                                                                  // rang en casos
                                                                                                  // negatius
                    } else if (characterData[0] == 1) {
                        return UPPERCASE[Math.floorMod(characterData[1] - 13, UPPERCASE.length)]; // floorMod per a
                                                                                                  // evitar sortir de
                                                                                                  // rang en casos
                                                                                                  // negatius
                    } else {
                        return character; // si no és ni a LOWERCASE ni a UPPERCASE el retornem tal qual
                    }
                })
                .map(character -> character.toString()) // convertim cada char en String i els unim amb
                                                        // Collectors.joining()
                .collect(Collectors.joining());
    }

    public static void main(String[] args) {
        String happyPath = "Nos Atacan Con Carbunco";
        String happyPathEncoded = xifraRot13(happyPath);
        String happyPathDecoded = desxifraRot13(happyPathEncoded);
        System.out.println("string feliç: " + happyPath);
        System.out.println("string feliç xifrat: " + happyPathEncoded);
        System.out.println("string feliç desxifrat: " + happyPathDecoded);

        String trickyPath = "çÇñÑ666 s̴t̴r̷i̵n̵g̷ ̵s̸a̵t̷à̴n̶i̵c̸";
        String trickyPathEncoded = xifraRot13(trickyPath);
        String trickyPathDecoded = desxifraRot13(trickyPathEncoded);
        System.out.println("string punyeter: " + trickyPath);
        System.out.println("string punyeter xifrat: " + trickyPathEncoded);
        System.out.println("string punyeter desxifrat: " + trickyPathDecoded);
    }
}
