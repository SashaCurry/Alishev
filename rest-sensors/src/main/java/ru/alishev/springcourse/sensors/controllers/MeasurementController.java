package ru.alishev.springcourse.sensors.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.sensors.dto.MeasurementDTO;
import ru.alishev.springcourse.sensors.models.Measurement;
import ru.alishev.springcourse.sensors.services.MeasurementService;
import ru.alishev.springcourse.sensors.util.MeasurementErrorResponse;
import ru.alishev.springcourse.sensors.util.MeasurementNotAddedException;
import ru.alishev.springcourse.sensors.util.MeasurementValidator;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;
    private final MeasurementValidator measurementValidator;

    @Autowired
    public MeasurementController(MeasurementService measurementService, ModelMapper modelMapper,
                                 MeasurementValidator measurementValidator) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
        this.measurementValidator = measurementValidator;
    }


    @GetMapping
    public ResponseEntity<List<MeasurementDTO>> getAllMeasurements() {
        List<Measurement> measurements = measurementService.findAll();

        List<MeasurementDTO> measurementDTOS = new ArrayList<>();
        for (Measurement measurement : measurements) {
            measurementDTOS.add(modelMapper.map(measurement, MeasurementDTO.class));
        }

        return new ResponseEntity<>(measurementDTOS, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO,
                                                     BindingResult bindingResult) {
        measurementValidator.validate(convertToMeasurement(measurementDTO), bindingResult);

        if (bindingResult.hasErrors()) {
            StringBuilder errorsMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors)
                errorsMsg.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("\n");

            throw new MeasurementNotAddedException(String.valueOf(errorsMsg));
        }

        measurementService.save(convertToMeasurement(measurementDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/rainyDaysCount")
    public ResponseEntity<Long> rainyDaysCount() {
        return new ResponseEntity<>(measurementService.countRaining(), HttpStatus.OK);
    }


    @ExceptionHandler
    public ResponseEntity<MeasurementErrorResponse> handleException(MeasurementNotAddedException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(e.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }
}
