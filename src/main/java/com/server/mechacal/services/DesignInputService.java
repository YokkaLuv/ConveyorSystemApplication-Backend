package com.server.mechacal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.mechacal.dto.DesignInputDto;
import com.server.mechacal.dto.Chapter.*;
import com.server.mechacal.entities.DesignInput;
import com.server.mechacal.entities.WorkSession;
import com.server.mechacal.entities.Chapter.*;
import com.server.mechacal.exceptions.SessionNotFoundException;
import com.server.mechacal.repositories.DesignInputRepository;
import com.server.mechacal.repositories.WorkSessionRepository;

@Service
public class DesignInputService {
    
    @Autowired
    private final DesignInputRepository designInputRepository;
    private final WorkSessionRepository workSessionRepository;

    public DesignInputService(DesignInputRepository designInputRepository,
                              WorkSessionRepository workSessionRepository)
    {
        this.designInputRepository = designInputRepository;
        this.workSessionRepository = workSessionRepository;
    }

    public DesignInputDto getDesignInputBySessionId(String sessionId) {
        WorkSession workSession = workSessionRepository.findById(sessionId)
            .orElseThrow(() -> new RuntimeException("WorkSession not found"));

        DesignInput designInput = workSession.getDesignInput();
        if (designInput == null) {
            throw new RuntimeException("DesignInput not found for this WorkSession");
        }

        DesignInputDto designInputDto = new DesignInputDto();
        designInputDto.update(designInput);
        return designInputDto;
    }

    public Chapter1Dto getChapter1(String sessionId) {
        WorkSession workSession = workSessionRepository.findById(sessionId)
            .orElseThrow(() -> new SessionNotFoundException("Session not found with id: " + sessionId));

        DesignInput designInput = workSession.getDesignInput();
        if (designInput == null) {
            throw new RuntimeException("DesignInput not found for this WorkSession");
        }

        Chapter1 chapter1 = designInput.getChap1();
        Chapter1Dto updatedDto = Chapter1Dto.from(chapter1);
        return updatedDto;
    }



    public Chapter1Dto updateChapter1(String sessionId, Chapter1Dto dto)
    {
        WorkSession workSession = workSessionRepository.findById(sessionId)
            .orElseThrow(() -> new SessionNotFoundException("WorkSession not found"));

        DesignInput designInput = workSession.getDesignInput();
        if (designInput == null) {
            throw new RuntimeException("DesignInput not found for this WorkSession");
        }

        Chapter1 chapter1 = designInput.getChap1();

        chapter1.setChainForce(dto.getChainForce());
        chapter1.setChainVelocity(dto.getChainVelocity());
        chapter1.setEfficiency(dto.getEfficiency());
        chapter1.setLoadFactor(dto.getLoadFactor());
        chapter1.setMotorSpeed(dto.getMotorSpeed());

        designInput.setChap1(chapter1);
        designInputRepository.save(designInput);
        Chapter1Dto updatedDto = Chapter1Dto.from(chapter1);
        return updatedDto;
    }

    public Chapter1Dto calculateChapter1Output(String sessionId) {
        WorkSession workSession = workSessionRepository.findById(sessionId)
            .orElseThrow(() -> new RuntimeException("WorkSession not found"));

        DesignInput designInput = workSession.getDesignInput();
        if (designInput == null) {
            throw new RuntimeException("DesignInput not found for this WorkSession");
        }

        Chapter1 chapter1 = designInput.getChap1();
        if (chapter1 == null) {
            throw new RuntimeException("Chapter 1 not found");
        }

        Chapter1Dto dto = Chapter1Dto.from(chapter1);
        dto.calculateOutput();

        return dto;
    }

    public Chapter1Dto saveChapter1Output(String sessionId) {
        WorkSession workSession = workSessionRepository.findById(sessionId)
            .orElseThrow(() -> new RuntimeException("WorkSession not found"));

        DesignInput designInput = workSession.getDesignInput();
        if (designInput == null) {
            throw new RuntimeException("DesignInput not found for this WorkSession");
        }

        Chapter1 chapter1 = designInput.getChap1();
        if (chapter1 == null) {
            throw new RuntimeException("Chapter 1 not found");
        }

        chapter1.calculateOutput();
        designInput.setChap1(chapter1);
        designInputRepository.save(designInput);

        return Chapter1Dto.from(chapter1);
    }

    public Chapter2Dto getChapter2(String sessionId) {
        WorkSession workSession = workSessionRepository.findById(sessionId)
            .orElseThrow(() -> new SessionNotFoundException("Session not found with id: " + sessionId));

        DesignInput designInput = workSession.getDesignInput();
        if (designInput == null) {
            throw new RuntimeException("DesignInput not found for this WorkSession");
        }

        Chapter2 chapter2 = designInput.getChap2();
        Chapter2Dto updatedDto = Chapter2Dto.from(chapter2);
        return updatedDto;
    }


    public Chapter2Dto updateChapter2(String sessionId, Chapter2Dto inputDto, boolean importFromPreviousChapters)
    {
        WorkSession workSession = workSessionRepository.findById(sessionId)
            .orElseThrow(() -> new RuntimeException("WorkSession not found"));

        DesignInput designInput = workSession.getDesignInput();
        if (designInput == null) {
            throw new RuntimeException("DesignInput not found for this WorkSession");
        }

        Chapter2 chapter2 = designInput.getChap2();
        if (chapter2 == null) {
            chapter2 = new Chapter2();
        }
    
        if (importFromPreviousChapters) {
            Chapter1 chapter1 = designInput.getChap1();
            if (chapter1 == null) {
                throw new RuntimeException("Chapter 1 not found to import data");
            }
            chapter2.setWorkingShaftPower(chapter1.getWorkingShaftPower());
            chapter2.setChainVelocity(chapter1.getChainVelocity());
            chapter2.setImported(true);
        } else {
            chapter2.setWorkingShaftPower(inputDto.getWorkingShaftPower());
            chapter2.setChainVelocity(inputDto.getChainVelocity());
            chapter2.setImported(false);
        }
    
        chapter2.setChainForce(inputDto.getChainForce());
        chapter2.setChainPitch(inputDto.getChainPitch());
        chapter2.setSprocketTeeth1(inputDto.getSprocketTeeth1());
        chapter2.setSprocketTeeth2(inputDto.getSprocketTeeth2());
    
        designInput.setChap2(chapter2);
        designInputRepository.save(designInput);
        Chapter2Dto updatedDto = Chapter2Dto.from(chapter2);
        return updatedDto;
    }

    public Chapter2Dto calculateChapter2Output(String sessionId) {
        WorkSession workSession = workSessionRepository.findById(sessionId)
            .orElseThrow(() -> new RuntimeException("WorkSession not found"));

        DesignInput designInput = workSession.getDesignInput();
        if (designInput == null) {
            throw new RuntimeException("DesignInput not found for this WorkSession");
        }

        Chapter2 chapter2 = designInput.getChap2();
        if (chapter2 == null) {
            throw new RuntimeException("Chapter 2 not found");
        }

        Chapter2Dto dto = Chapter2Dto.from(chapter2);
        dto.calculateOutput();

        return dto;
    }

    public Chapter2Dto saveChapter2Output(String sessionId) {
        WorkSession workSession = workSessionRepository.findById(sessionId)
            .orElseThrow(() -> new RuntimeException("WorkSession not found"));

        DesignInput designInput = workSession.getDesignInput();
        if (designInput == null) {
            throw new RuntimeException("DesignInput not found for this WorkSession");
        }

        Chapter2 chapter2 = designInput.getChap2();
        if (chapter2 == null) {
            throw new RuntimeException("Chapter 2 not found");
        }

        chapter2.calculateOutput();
        designInput.setChap2(chapter2);
        designInputRepository.save(designInput);

        return Chapter2Dto.from(chapter2);
    }

    public Chapter3Dto getChapter3(String sessionId) {
        WorkSession workSession = workSessionRepository.findById(sessionId)
            .orElseThrow(() -> new SessionNotFoundException("Session not found with id: " + sessionId));

        DesignInput designInput = workSession.getDesignInput();
        if (designInput == null) {
            throw new RuntimeException("DesignInput not found for this WorkSession");
        }

        Chapter3 chapter3 = designInput.getChap3();
        Chapter3Dto updatedDto = Chapter3Dto.from(chapter3);
        return updatedDto;
    }


    public Chapter3Dto updateChapter3(String sessionId, Chapter3Dto inputDto, boolean importFromPreviousChapters)
    {
        WorkSession workSession = workSessionRepository.findById(sessionId)
            .orElseThrow(() -> new RuntimeException("WorkSession not found"));

        DesignInput designInput = workSession.getDesignInput();
        if (designInput == null) {
            throw new RuntimeException("DesignInput not found for this WorkSession");
        }

        Chapter3 chapter3 = designInput.getChap3();
        if (chapter3 == null) {
            chapter3 = new Chapter3();
        }
    
        if (importFromPreviousChapters) {
            Chapter1 chapter1 = designInput.getChap1();
            if (chapter1 == null) {
                throw new RuntimeException("Chapter 1 not found to import data");
            }
            Chapter2 chapter2 = designInput.getChap2();
            if (chapter2 == null) {
                throw new RuntimeException("Chapter 2 not found to import data");
            }
            chapter3.setMotorSpeed(chapter1.getMotorSpeed());
            chapter3.setSprocketRotateSpd(chapter2.getSprocketRotateSpd());
            chapter3.setChainRatio(chapter2.getChainRatio());
            chapter3.setImported(true);
        } else {
            chapter3.setMotorSpeed(inputDto.getMotorSpeed());
            chapter3.setSprocketRotateSpd(inputDto.getSprocketRotateSpd());
            chapter3.setChainRatio(inputDto.getChainRatio());
            chapter3.setImported(false);
        }
    
        chapter3.setTotalRatio(inputDto.getTotalRatio());
        chapter3.setFastRatio(inputDto.getFastRatio());
        chapter3.setSlowRatio(inputDto.getSlowRatio());
    
        designInput.setChap3(chapter3);
        designInputRepository.save(designInput);
        Chapter3Dto updatedDto = Chapter3Dto.from(chapter3);
        return updatedDto;
    }

    public Chapter3Dto calculateChapter3Output(String sessionId) {
        WorkSession workSession = workSessionRepository.findById(sessionId)
            .orElseThrow(() -> new RuntimeException("WorkSession not found"));

        DesignInput designInput = workSession.getDesignInput();
        if (designInput == null) {
            throw new RuntimeException("DesignInput not found for this WorkSession");
        }

        Chapter3 chapter3 = designInput.getChap3();
        if (chapter3 == null) {
            throw new RuntimeException("Chapter 3 not found");
        }

        Chapter3Dto dto = Chapter3Dto.from(chapter3);
        dto.calculateOutput();

        return dto;
    }

    public Chapter3Dto saveChapter3Output(String sessionId) {
        WorkSession workSession = workSessionRepository.findById(sessionId)
            .orElseThrow(() -> new RuntimeException("WorkSession not found"));

        DesignInput designInput = workSession.getDesignInput();
        if (designInput == null) {
            throw new RuntimeException("DesignInput not found for this WorkSession");
        }

        Chapter3 chapter3 = designInput.getChap3();
        if (chapter3 == null) {
            throw new RuntimeException("Chapter 3 not found");
        }

        chapter3.calculateOutput();
        designInput.setChap3(chapter3);
        designInputRepository.save(designInput);

        return Chapter3Dto.from(chapter3);
    }

    public Chapter4Dto getChapter4(String sessionId) {
        WorkSession workSession = workSessionRepository.findById(sessionId)
            .orElseThrow(() -> new SessionNotFoundException("Session not found with id: " + sessionId));

        DesignInput designInput = workSession.getDesignInput();
        if (designInput == null) {
            throw new RuntimeException("DesignInput not found for this WorkSession");
        }

        Chapter4 chapter4 = designInput.getChap4();
        Chapter4Dto updatedDto = Chapter4Dto.from(chapter4);
        return updatedDto;
    }


    public Chapter4Dto updateChapter4(String sessionId, Chapter4Dto inputDto, boolean importFromPreviousChapters)
    {
        WorkSession workSession = workSessionRepository.findById(sessionId)
            .orElseThrow(() -> new RuntimeException("WorkSession not found"));

        DesignInput designInput = workSession.getDesignInput();
        if (designInput == null) {
            throw new RuntimeException("DesignInput not found for this WorkSession");
        }

        Chapter4 chapter4 = designInput.getChap4();
        if (chapter4 == null) {
            chapter4 = new Chapter4();
        }
    
        if (importFromPreviousChapters) {
            Chapter1 chapter1 = designInput.getChap1();
            if (chapter1 == null) {
                throw new RuntimeException("Chapter 1 not found to import data");
            }
            chapter4.setTorque(chapter1.getTorque());
            chapter4.setMotorSpeed(chapter1.getMotorSpeed());
            chapter4.setImported(true);
        } else {
            chapter4.setTorque(inputDto.getTorque());
            chapter4.setMotorSpeed(inputDto.getMotorSpeed());
            chapter4.setImported(false);
        }
    
        chapter4.setMotorShaftDiameter(inputDto.getMotorShaftDiameter());
    
        designInput.setChap4(chapter4);
        designInputRepository.save(designInput);
        Chapter4Dto updatedDto = Chapter4Dto.from(chapter4);
        return updatedDto;
    }

    public Chapter4Dto calculateChapter4Output(String sessionId) {
        WorkSession workSession = workSessionRepository.findById(sessionId)
            .orElseThrow(() -> new RuntimeException("WorkSession not found"));

        DesignInput designInput = workSession.getDesignInput();
        if (designInput == null) {
            throw new RuntimeException("DesignInput not found for this WorkSession");
        }

        Chapter4 chapter4 = designInput.getChap4();
        if (chapter4 == null) {
            throw new RuntimeException("Chapter 4 not found");
        }

        Chapter4Dto dto = Chapter4Dto.from(chapter4);
        dto.calculateOutput();

        return dto;
    }

    public Chapter4Dto saveChapter4Output(String sessionId) {
        WorkSession workSession = workSessionRepository.findById(sessionId)
            .orElseThrow(() -> new RuntimeException("WorkSession not found"));

        DesignInput designInput = workSession.getDesignInput();
        if (designInput == null) {
            throw new RuntimeException("DesignInput not found for this WorkSession");
        }

        Chapter4 chapter4 = designInput.getChap4();
        if (chapter4 == null) {
            throw new RuntimeException("Chapter 4 not found");
        }

        chapter4.calculateOutput();
        designInput.setChap4(chapter4);
        designInputRepository.save(designInput);

        return Chapter4Dto.from(chapter4);
    }

    public Chapter5Dto getChapter5(String sessionId) {
        WorkSession workSession = workSessionRepository.findById(sessionId)
            .orElseThrow(() -> new SessionNotFoundException("Session not found with id: " + sessionId));

        DesignInput designInput = workSession.getDesignInput();
        if (designInput == null) {
            throw new RuntimeException("DesignInput not found for this WorkSession");
        }

        Chapter5 chapter5 = designInput.getChap5();
        Chapter5Dto updatedDto = Chapter5Dto.from(chapter5);
        return updatedDto;
    }


    public Chapter5Dto updateChapter5(String sessionId, Chapter5Dto inputDto, boolean importFromPreviousChapters)
    {
        WorkSession workSession = workSessionRepository.findById(sessionId)
            .orElseThrow(() -> new RuntimeException("WorkSession not found"));

        DesignInput designInput = workSession.getDesignInput();
        if (designInput == null) {
            throw new RuntimeException("DesignInput not found for this WorkSession");
        }

        Chapter5 chapter5 = designInput.getChap5();
        if (chapter5 == null) {
            chapter5 = new Chapter5();
        }
    
        if (importFromPreviousChapters) {
            Chapter1 chapter1 = designInput.getChap1();
            if (chapter1 == null) {
                throw new RuntimeException("Chapter 1 not found to import data");
            }
            Chapter2 chapter2 = designInput.getChap2();
            if (chapter2 == null) {
                throw new RuntimeException("Chapter 2 not found to import data");
            }
            chapter5.setChainForce(chapter1.getChainForce());
            chapter5.setChainVelocity(chapter1.getChainVelocity());
            chapter5.setImported(true);
        } else {
            chapter5.setChainForce(inputDto.getChainForce());
            chapter5.setChainVelocity(inputDto.getChainVelocity());
            chapter5.setImported(false);
        }
    
        chapter5.setServiceLife(inputDto.getServiceLife());
    
        designInput.setChap5(chapter5);
        designInputRepository.save(designInput);
        Chapter5Dto updatedDto = Chapter5Dto.from(chapter5);
        return updatedDto;
    }

    public Chapter5Dto calculateChapter5Output(String sessionId) {
        WorkSession workSession = workSessionRepository.findById(sessionId)
            .orElseThrow(() -> new RuntimeException("WorkSession not found"));

        DesignInput designInput = workSession.getDesignInput();
        if (designInput == null) {
            throw new RuntimeException("DesignInput not found for this WorkSession");
        }

        Chapter5 chapter5 = designInput.getChap5();
        if (chapter5 == null) {
            throw new RuntimeException("Chapter 5 not found");
        }

        Chapter5Dto dto = Chapter5Dto.from(chapter5);
        dto.calculateOutput();

        return dto;
    }

    public Chapter5Dto saveChapter5Output(String sessionId) {
        WorkSession workSession = workSessionRepository.findById(sessionId)
            .orElseThrow(() -> new RuntimeException("WorkSession not found"));

        DesignInput designInput = workSession.getDesignInput();
        if (designInput == null) {
            throw new RuntimeException("DesignInput not found for this WorkSession");
        }

        Chapter5 chapter5 = designInput.getChap5();
        if (chapter5 == null) {
            throw new RuntimeException("Chapter 5 not found");
        }

        chapter5.calculateOutput();
        designInput.setChap5(chapter5);
        designInputRepository.save(designInput);

        return Chapter5Dto.from(chapter5);
    }

    public Chapter6Dto getChapter6(String sessionId) {
        WorkSession workSession = workSessionRepository.findById(sessionId)
            .orElseThrow(() -> new SessionNotFoundException("Session not found with id: " + sessionId));

        DesignInput designInput = workSession.getDesignInput();
        if (designInput == null) {
            throw new RuntimeException("DesignInput not found for this WorkSession");
        }

        Chapter6 chapter6 = designInput.getChap6();
        Chapter6Dto updatedDto = Chapter6Dto.from(chapter6);
        return updatedDto;
    }


    public Chapter6Dto updateChapter6(String sessionId, Chapter6Dto inputDto, boolean importFromPreviousChapters)
    {
        WorkSession workSession = workSessionRepository.findById(sessionId)
            .orElseThrow(() -> new RuntimeException("WorkSession not found"));

        DesignInput designInput = workSession.getDesignInput();
        if (designInput == null) {
            throw new RuntimeException("DesignInput not found for this WorkSession");
        }

        Chapter6 chapter6 = designInput.getChap6();
        if (chapter6 == null) {
            chapter6 = new Chapter6();
        }
    
        if (importFromPreviousChapters) {
            Chapter1 chapter1 = designInput.getChap1();
            if (chapter1 == null) {
                throw new RuntimeException("Chapter 1 not found to import data");
            }
            Chapter2 chapter2 = designInput.getChap2();
            if (chapter2 == null) {
                throw new RuntimeException("Chapter 2 not found to import data");
            }
            Chapter3 chapter3 = designInput.getChap3();
            if (chapter3 == null) {
                throw new RuntimeException("Chapter 3 not found to import data");
            }
            Chapter4 chapter4 = designInput.getChap4();
            if (chapter4 == null) {
                throw new RuntimeException("Chapter 4 not found to import data");
            }
            chapter6.setWorkingShaftPower(chapter1.getWorkingShaftPower());
            chapter6.setMotorSpeed(chapter1.getMotorSpeed());
            chapter6.setFastRatio(chapter3.getFastRatio());
            chapter6.setSlowRatio(chapter3.getSlowRatio());
            chapter6.setMinimalShaftDiameter(chapter4.getMinimalShaftDiameter());
            chapter6.setSprocketTeeth1(chapter2.getSprocketTeeth1());
            chapter6.setSprocketTeeth2(chapter2.getSprocketTeeth2());
            chapter6.setImported(true);
        } else {
            chapter6.setWorkingShaftPower(inputDto.getWorkingShaftPower());
            chapter6.setMotorSpeed(inputDto.getMotorSpeed());
            chapter6.setFastRatio(inputDto.getFastRatio());
            chapter6.setSlowRatio(inputDto.getSlowRatio());
            chapter6.setMinimalShaftDiameter(inputDto.getMinimalShaftDiameter());
            chapter6.setSprocketTeeth1(inputDto.getSprocketTeeth1());
            chapter6.setSprocketTeeth2(inputDto.getSprocketTeeth2());
            chapter6.setImported(true);
        }
    
        designInput.setChap6(chapter6);
        designInputRepository.save(designInput);
        Chapter6Dto updatedDto = Chapter6Dto.from(chapter6);
        return updatedDto;
    }

    public Chapter6Dto calculateChapter6Output(String sessionId) {
        WorkSession workSession = workSessionRepository.findById(sessionId)
            .orElseThrow(() -> new RuntimeException("WorkSession not found"));

        DesignInput designInput = workSession.getDesignInput();
        if (designInput == null) {
            throw new RuntimeException("DesignInput not found for this WorkSession");
        }

        Chapter6 chapter6 = designInput.getChap6();
        if (chapter6 == null) {
            throw new RuntimeException("Chapter 6 not found");
        }

        Chapter6Dto dto = Chapter6Dto.from(chapter6);
        dto.calculateOutput();

        return dto;
    }

    public Chapter6Dto saveChapter6Output(String sessionId) {
        WorkSession workSession = workSessionRepository.findById(sessionId)
            .orElseThrow(() -> new RuntimeException("WorkSession not found"));

        DesignInput designInput = workSession.getDesignInput();
        if (designInput == null) {
            throw new RuntimeException("DesignInput not found for this WorkSession");
        }

        Chapter6 chapter6 = designInput.getChap6();
        if (chapter6 == null) {
            throw new RuntimeException("Chapter 6 not found");
        }

        chapter6.calculateOutput();
        designInput.setChap6(chapter6);
        designInputRepository.save(designInput);

        return Chapter6Dto.from(chapter6);
    }

    public Chapter7Dto getChapter7(String sessionId) {
        WorkSession workSession = workSessionRepository.findById(sessionId)
            .orElseThrow(() -> new SessionNotFoundException("Session not found with id: " + sessionId));

        DesignInput designInput = workSession.getDesignInput();
        if (designInput == null) {
            throw new RuntimeException("DesignInput not found for this WorkSession");
        }

        Chapter7 chapter7 = designInput.getChap7();
        Chapter7Dto updatedDto = Chapter7Dto.from(chapter7);
        return updatedDto;
    }


    public Chapter7Dto updateChapter7(String sessionId, Chapter7Dto inputDto, boolean importFromPreviousChapters)
    {
        WorkSession workSession = workSessionRepository.findById(sessionId)
            .orElseThrow(() -> new RuntimeException("WorkSession not found"));

        DesignInput designInput = workSession.getDesignInput();
        if (designInput == null) {
            throw new RuntimeException("DesignInput not found for this WorkSession");
        }

        Chapter7 chapter7 = designInput.getChap7();
        if (chapter7 == null) {
            chapter7 = new Chapter7();
        }
    
        if (importFromPreviousChapters) {
            Chapter1 chapter1 = designInput.getChap1();
            if (chapter1 == null) {
                throw new RuntimeException("Chapter 1 not found to import data");
            }
            Chapter2 chapter2 = designInput.getChap2();
            if (chapter2 == null) {
                throw new RuntimeException("Chapter 2 not found to import data");
            }
            Chapter6 chapter6 = designInput.getChap6();
            if (chapter6 == null) {
                throw new RuntimeException("Chapter 6 not found to import data");
            }
            chapter7.setWorkingShaftPower(chapter1.getWorkingShaftPower());
            chapter7.setSlowTorque(chapter6.getSlowTorque());
            chapter7.setPitchDiametersprocket3(chapter6.getPitchDiametersprocket3());
            chapter7.setContactStress(chapter6.getContactStress());
            chapter7.setImported(true);
        } else {
            chapter7.setWorkingShaftPower(inputDto.getWorkingShaftPower());
            chapter7.setSlowTorque(inputDto.getSlowTorque());
            chapter7.setPitchDiametersprocket3(inputDto.getPitchDiametersprocket3());
            chapter7.setContactStress(inputDto.getContactStress());
            chapter7.setImported(false);
        }
    
        chapter7.setRatedLoad(inputDto.getRatedLoad());
    
        designInput.setChap7(chapter7);
        designInputRepository.save(designInput);
        Chapter7Dto updatedDto = Chapter7Dto.from(chapter7);
        return updatedDto;
    }

    public Chapter7Dto calculateChapter7Output(String sessionId) {
        WorkSession workSession = workSessionRepository.findById(sessionId)
            .orElseThrow(() -> new RuntimeException("WorkSession not found"));

        DesignInput designInput = workSession.getDesignInput();
        if (designInput == null) {
            throw new RuntimeException("DesignInput not found for this WorkSession");
        }

        Chapter7 chapter7 = designInput.getChap7();
        if (chapter7 == null) {
            throw new RuntimeException("Chapter 7 not found");
        }

        Chapter7Dto dto = Chapter7Dto.from(chapter7);
        dto.calculateOutput();

        return dto;
    }

    public Chapter7Dto saveChapter7Output(String sessionId) {
        WorkSession workSession = workSessionRepository.findById(sessionId)
            .orElseThrow(() -> new RuntimeException("WorkSession not found"));

        DesignInput designInput = workSession.getDesignInput();
        if (designInput == null) {
            throw new RuntimeException("DesignInput not found for this WorkSession");
        }

        Chapter7 chapter7 = designInput.getChap7();
        if (chapter7 == null) {
            throw new RuntimeException("Chapter 7 not found");
        }

        chapter7.calculateOutput();
        designInput.setChap7(chapter7);
        designInputRepository.save(designInput);

        return Chapter7Dto.from(chapter7);
    }
}
