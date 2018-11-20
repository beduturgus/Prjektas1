package projektas1.demo.service;


import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SortingService {
    private String addFilesTextToOneString(List<MultipartFile> list) {
        return list.parallelStream()
                .map(it -> {
                    try {
                        return it.getBytes();
                    } catch (IOException e) {
                        return "".getBytes();
                    }
                })
                .map(it -> new String(it, StandardCharsets.UTF_8))
                .collect(Collectors.joining(" "));
    }

    private Map<String, Integer> createFrequencyMap(String filesText) {
        return Stream.of(filesText.split(" "))
                .filter(it -> it.matches("^[A-Za-z]*$"))
                .filter(StringUtils::hasText)
                .map(String::toUpperCase)
                .collect(Collectors.toMap(
                        it -> it.substring(0, 1),
                        it -> 1,
                        (_old, _new) -> _old + _new)
                );
    }

    public String calculateFrequencey(List<MultipartFile> list) {
        String lines = addFilesTextToOneString(list);
        Map<String, Integer> fullList = createFrequencyMap(lines);
        return createResponse(fullList);
    }

    private String createResponse(final Map<String, Integer> fullList) {
        return fullList.entrySet()
                .stream()
                .sorted((o1, o2) -> o1.getKey().compareToIgnoreCase(o2.getKey()))
                .map(it -> it.getKey() + " => " + it.getValue() + "\n")
                .collect(Collectors.joining());
    }


}
