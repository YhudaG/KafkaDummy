package cognyte.solution.controller;

import cognyte.solution.exceptionHandler.AppException;
import cognyte.solution.model.ApiError;
import cognyte.solution.model.RequestBodyModel;
import cognyte.solution.service.Producer;
import cognyte.solution.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class SolutionController {

    @Autowired
    private  SolutionService solutionService;

    @Autowired
    private  Producer producer;

    @PostMapping("/sendMessage")
    public String sendMessage(@RequestBody @NonNull RequestBodyModel requestBodyModel) throws Exception, ApiError {
        return solutionService.transferMessage(requestBodyModel);
    }

    @GetMapping("/getLast")
    public Map<String, String>  getLast() throws AppException {
        return solutionService.getLast();
    }

}
