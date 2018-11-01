package com.xgh.model.command.operational.bed;

import com.xgh.infra.controller.BasicCommandController;
import com.xgh.model.command.operational.bed.commands.DeleteBed;
import com.xgh.model.command.operational.bed.commands.RegisterBed;
import com.xgh.model.command.operational.bed.commands.UpdateBed;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bed")
public class BedCommandController extends BasicCommandController<RegisterBed, UpdateBed, DeleteBed> {
    @Override
    protected String getBasePath() {
        return "/bed";
    }
}
