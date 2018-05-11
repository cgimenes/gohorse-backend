package com.xgh.model.command.bed;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xgh.infra.controller.BasicCommandController;
import com.xgh.model.command.bed.commands.DeleteBed;
import com.xgh.model.command.bed.commands.RegisterBed;
import com.xgh.model.command.bed.commands.UpdateBed;

@RestController
@RequestMapping("/bed")
public class BedCommandController extends BasicCommandController<RegisterBed, UpdateBed, DeleteBed>{
    @Override
    protected String getBasePath() {
        return "/bed";
    }
}
