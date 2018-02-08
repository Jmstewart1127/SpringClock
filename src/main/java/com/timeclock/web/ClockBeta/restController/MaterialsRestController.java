package com.timeclock.web.ClockBeta.restController;

import com.timeclock.web.ClockBeta.model.Material;
import com.timeclock.web.ClockBeta.model.Schedule;
import com.timeclock.web.ClockBeta.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MaterialsRestController {

    @Autowired
    MaterialService materialService;

    @CrossOrigin(origins = {"https://spring-clock-ui.herokuapp.com", "http://localhost:3000"})
    @RequestMapping("/rest/jobs/{jobId}/material")
    public Iterable<Material> getMaterialsByJobId(@PathVariable int jobId) {
        return materialService.findByJobId(jobId);
    }

    @CrossOrigin(origins = {"https://spring-clock-ui.herokuapp.com", "http://localhost:3000"})
    @RequestMapping("/rest/jobs/{jobId}/total/cost")
    public double getTotalCostOfAllMaterialsForJob(@PathVariable int jobId) {
        return materialService.totalPriceOfAllJobMaterials(jobId);
    }

    @CrossOrigin(origins = {"https://spring-clock-ui.herokuapp.com", "http://localhost:3000"})
    @RequestMapping(value = "/rest/material/add", method = RequestMethod.POST)
    public ResponseEntity<String> addNewMaterial(@RequestBody Material material) {
        materialService.saveMaterial(material);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @CrossOrigin(origins = {"https://spring-clock-ui.herokuapp.com", "http://localhost:3000"})
    @RequestMapping(value = "/rest/material/update", method = RequestMethod.PUT)
    public ResponseEntity<String> updateMaterial(@RequestBody Material material) {
        materialService.saveMaterial(material);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @CrossOrigin(origins = {"https://spring-clock-ui.herokuapp.com", "http://localhost:3000"})
    @RequestMapping(value = "/rest/material/delete", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteMaterial(@RequestBody Material material) {
        materialService.deleteMaterial(material);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
