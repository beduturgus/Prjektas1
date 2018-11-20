package projektas1.demo.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import projektas1.demo.service.SortingService;

import java.util.List;

@RestController
public class FileController {
    private final SortingService sortingService;

    public FileController(final SortingService sortingService) {
        this.sortingService = sortingService;
    }

    @PostMapping(path = "/consume")
    public ResponseEntity<String> consumeFile(@RequestPart(name = "file") List<MultipartFile> files) {

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"response.log\"")
                .body(sortingService.calculateFrequencey(files));
    }
}

