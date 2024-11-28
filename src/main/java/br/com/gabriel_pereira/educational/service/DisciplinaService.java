package br.com.gabriel_pereira.educational.service;

import br.com.gabriel_pereira.educational.dto.DisciplinaDto;
import br.com.gabriel_pereira.educational.dto.CursoDto;
import br.com.gabriel_pereira.educational.dto.ProfessorDto;
import br.com.gabriel_pereira.educational.model.CursoModel;
import br.com.gabriel_pereira.educational.model.DisciplinaModel;
import br.com.gabriel_pereira.educational.repository.DisciplinaRepository;
import br.com.gabriel_pereira.educational.service.exception.ResourceCodeAlreadyExistsException;
import br.com.gabriel_pereira.educational.service.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;
    private final CursoService cursoService;
    private final ProfessorService professorService;

    public DisciplinaService(DisciplinaRepository disciplinaRepository, CursoService cursoService, ProfessorService professorService) {
        this.disciplinaRepository = disciplinaRepository;
        this.cursoService = cursoService;
        this.professorService = professorService;
    }

    public void createDisciplina(DisciplinaDto disciplinaDto) {

        verifyCodigo(disciplinaDto);

        CursoDto cursoDto = getCursoDto(disciplinaDto);
        ProfessorDto professorDto = getProfessorDto(disciplinaDto);

        disciplinaDto.setCursoDto(cursoDto);
        disciplinaDto.setProfessorDto(professorDto);

        DisciplinaModel disciplinaModel = CONVERT_DTO_TO_MODEL(disciplinaDto);

        disciplinaRepository.save(disciplinaModel);
    }

    public void updateDisciplina(DisciplinaDto disciplinaDto, Integer id) {

        DisciplinaDto verifyDisciplinaDto = findById(id);

        if(!verifyDisciplinaDto.getCodigo().equals(disciplinaDto.getCodigo())) {
            verifyCodigo(disciplinaDto);
        }

        CursoDto CursoDto = getCursoDto(disciplinaDto);
        ProfessorDto professorDto = getProfessorDto(disciplinaDto);

        disciplinaDto.setCursoDto(CursoDto);
        disciplinaDto.setProfessorDto(professorDto);

        DisciplinaModel disciplinaModel = CONVERT_DTO_TO_MODEL(disciplinaDto);
        disciplinaModel.setId(id);
        disciplinaRepository.save(disciplinaModel);
    }

    public DisciplinaDto findById (Integer id) {
        DisciplinaModel disciplinaModel = disciplinaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id não foi encontrado"));

        return CONVERT_MODEL_TO_DTO(disciplinaModel);
    }

    public Set<DisciplinaDto> findAll () {
        List<DisciplinaModel> lstDisciplinaModel = disciplinaRepository.findAll();
        Set<DisciplinaDto> lstDisciplinaDto = new HashSet<>();

        for(DisciplinaModel disciplinaModel : lstDisciplinaModel) {
            lstDisciplinaDto.add(CONVERT_MODEL_TO_DTO(disciplinaModel));
        }

        return lstDisciplinaDto;
    }

    public void deleteDisciplina(Integer id) {
        DisciplinaDto verifyDisciplinaDto = findById(id);
        DisciplinaModel disciplinaModel = CONVERT_DTO_TO_MODEL(verifyDisciplinaDto);
        disciplinaModel.setId(id);
        disciplinaRepository.delete(disciplinaModel);
    }

    private void verifyCodigo(DisciplinaDto disciplinaDto) {
        Optional<DisciplinaModel> DisciplinaModelOptional = disciplinaRepository.findByCodigo(disciplinaDto.getCodigo());

        if(DisciplinaModelOptional.isPresent()) {
            throw new ResourceCodeAlreadyExistsException("Já existe uma disciplina com esse código");
        }
    }

    private CursoDto getCursoDto(DisciplinaDto disciplinaDto) {
        CursoDto cursoDto;
        try {
            cursoDto = cursoService.findById(disciplinaDto.getCursoId());
        } catch (ResourceNotFoundException ex) {
            throw new ResourceNotFoundException("curso_id não foi encontrado");
        }
        return cursoDto;
    }

    private ProfessorDto getProfessorDto (DisciplinaDto disciplinaDto) {
        ProfessorDto professorDto;

        try {
            professorDto = professorService.findById(disciplinaDto.getProfessorId());
        } catch (ResourceNotFoundException ex) {
            throw new ResourceNotFoundException("professor_id não foi encontrado");
        }

        return professorDto;
    }

    public static DisciplinaModel CONVERT_DTO_TO_MODEL(DisciplinaDto disciplinaDto) {
        DisciplinaModel disciplinaModel = new DisciplinaModel();
        disciplinaModel.setId(disciplinaDto.getId() != null ? disciplinaDto.getId() : null);
        disciplinaModel.setNome(disciplinaDto.getNome());
        disciplinaModel.setCodigo(disciplinaDto.getCodigo());
        disciplinaModel.setCursoModel(disciplinaDto.getCursoDto() != null ? CursoService.CONVERT_DTO_TO_MODEL(disciplinaDto.getCursoDto()) : null);
        disciplinaModel.setProfessorModel(disciplinaDto.getProfessorDto() != null ? ProfessorService.COVERT_DTO_TO_MODEL(disciplinaDto.getProfessorDto()) : null);

        return disciplinaModel;
    }

    public static DisciplinaDto CONVERT_MODEL_TO_DTO(DisciplinaModel disciplinaModel) {
        DisciplinaDto disciplinaDto = new DisciplinaDto();
        disciplinaDto.setId(disciplinaModel.getId() != null ? disciplinaModel.getId() : null);
        disciplinaDto.setNome(disciplinaModel.getNome());
        disciplinaDto.setCodigo(disciplinaModel.getCodigo());
        disciplinaDto.setCursoId(disciplinaModel.getCursoModel().getId() != null ? disciplinaModel.getCursoModel().getId() : null);
        disciplinaDto.setProfessorId(disciplinaModel.getProfessorModel().getId() != null ? disciplinaModel.getProfessorModel().getId() : null);
        disciplinaDto.setCursoDto(disciplinaModel.getCursoModel() != null ? CursoService.CONVERT_MODEL_TO_DTO(disciplinaModel.getCursoModel()) : null);
        disciplinaDto.setProfessorDto(disciplinaModel.getProfessorModel() != null ? ProfessorService.COVERT_MODEL_TO_DTO(disciplinaModel.getProfessorModel()) : null);

        return disciplinaDto;
    }

}
