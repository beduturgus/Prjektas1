package projektas1.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
public class FileController {


    @PostMapping("/consume")
    public String consumeFile(@RequestPart(name = "file")List<MultipartFile> files
    ) throws IOException {
        SortingService service = new SortingService();
        service.initDownload(files);

        return
        "<a href=\"/GeneratedFiles/FileOne\" download>First file</a>" +
        "<a href=\"/GeneratedFiles/FileTwo\" download>Second file</a>" +
        "<a href=\"/GeneratedFiles/FileThree\" download>Third File</a>";
    }
}

