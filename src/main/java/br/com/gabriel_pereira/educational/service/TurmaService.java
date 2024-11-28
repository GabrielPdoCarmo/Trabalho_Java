package br.com.gabriel_pereira.educational.service;

import br.com.gabriel_pereira.educational.dto.TurmaDto;
import br.com.gabriel_pereira.educational.dto.CursoDto;
import br.com.gabriel_pereira.educational.model.TurmaModel;
import br.com.gabriel_pereira.educational.repository.TurmaRepository;
import br.com.gabriel_pereira.educational.service.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;

    private final CursoService cursoService;

    public TurmaService(TurmaRepository turmaRepository, CursoService cursoService) {
        this.turmaRepository = turmaRepository;
        this.cursoService = cursoService;
    }

    public void createTurma(TurmaDto turmaDto) {

        CursoDto cursoDto = getCursoDto(turmaDto);

        turmaDto.setCursoDto(cursoDto);

        TurmaModel turmaModel = CONVERT_DTO_TO_MODEL(turmaDto);

        turmaRepository.save(turmaModel);
    }

    public void updateTurma(TurmaDto turmaDto, Integer id) {

        CursoDto cursoDto = getCursoDto(turmaDto);

        turmaDto.setCursoDto(cursoDto);

        TurmaModel turmaModel = CONVERT_DTO_TO_MODEL(turmaDto);
        turmaModel.setId(id);
        turmaRepository.save(turmaModel);
    }

    public TurmaDto findById (Integer id) {
        TurmaModel turmaModel = turmaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id não foi encontrado"));

        return CONVERT_MODEL_TO_DTO(turmaModel);
    }

    public Set<TurmaDto> searchTurma (Integer ano, Integer semestre, Integer cursoId) {
        Set<TurmaModel> lstTurmaModel = turmaRepository.searchTurmas(ano, semestre, cursoId);
        Set<TurmaDto> lstTurmaDto = new HashSet<>();

        for(TurmaModel turmaModel : lstTurmaModel) {
            lstTurmaDto.add(CONVERT_MODEL_TO_DTO(turmaModel));
        }

        return lstTurmaDto;
    }

    public void deleteTurma(Integer id) {
        TurmaDto verifyTurmaDto = findById(id);
        TurmaModel turmaModel = CONVERT_DTO_TO_MODEL(verifyTurmaDto);
        turmaModel.setId(id);
        turmaRepository.delete(turmaModel);
    }

    private CursoDto getCursoDto(TurmaDto turmaDto) {
        CursoDto cursoDto;
        try {
            cursoDto = cursoService.findById(turmaDto.getCursoId());
        } catch (ResourceNotFoundException ex) {
            throw new ResourceNotFoundException("curso_id não foi encontrado");
        }
        return cursoDto;
    }

    public static TurmaModel CONVERT_DTO_TO_MODEL(TurmaDto turmaDto) {
        TurmaModel turmaModel = new TurmaModel();
        turmaModel.setId(turmaDto.getId() != null ? turmaDto.getId() : null);
        turmaModel.setAno(turmaDto.getAno());
        turmaModel.setSemestre(turmaDto.getSemestre());
        turmaModel.setCursoModel(turmaDto.getCursoDto() != null ? CursoService.CONVERT_DTO_TO_MODEL(turmaDto.getCursoDto()) : null);

        return turmaModel;
    }

    public static TurmaDto CONVERT_MODEL_TO_DTO(TurmaModel turmaModel) {
        TurmaDto turmaDto = new TurmaDto();
        turmaDto.setId(turmaModel.getId() != null ? turmaModel.getId() : null);
        turmaDto.setAno(turmaModel.getAno());
        turmaDto.setSemestre(turmaModel.getSemestre());
        turmaDto.setCursoId(turmaModel.getCursoModel().getId() != null ? turmaModel.getCursoModel().getId() : null);
        turmaDto.setCursoDto(turmaModel.getCursoModel() != null ? CursoService.CONVERT_MODEL_TO_DTO(turmaModel.getCursoModel()) : null);

        return turmaDto;
    }

}
