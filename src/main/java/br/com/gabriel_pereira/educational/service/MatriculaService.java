package br.com.gabriel_pereira.educational.service;

import br.com.gabriel_pereira.educational.dto.TurmaDto;
import br.com.gabriel_pereira.educational.dto.MatriculaDto;
import br.com.gabriel_pereira.educational.dto.MatriculaDto;
import br.com.gabriel_pereira.educational.dto.AlunoDto;
import br.com.gabriel_pereira.educational.model.TurmaModel;
import br.com.gabriel_pereira.educational.model.MatriculaModel;
import br.com.gabriel_pereira.educational.model.MatriculaModel;
import br.com.gabriel_pereira.educational.model.AlunoModel;
import br.com.gabriel_pereira.educational.repository.MatriculaRepository;
import br.com.gabriel_pereira.educational.service.exception.ResourceCodeAlreadyExistsException;
import br.com.gabriel_pereira.educational.service.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MatriculaService {

    private final MatriculaRepository matriculaRepository;
    private final AlunoService alunoService;
    private final TurmaService turmaService;

    public MatriculaService(MatriculaRepository matriculaRepository, AlunoService alunoService, TurmaService turmaService) {
        this.matriculaRepository = matriculaRepository;
        this.alunoService = alunoService;
        this.turmaService = turmaService;
    }

    public void createdMatriculation (MatriculaDto matriculaDto) {
        AlunoDto alunoDto = getStudent(matriculaDto);
        TurmaDto turmaDto = getClass(matriculaDto);

        verifyStudentIsMatriculate(alunoDto, turmaDto);

        matriculaDto.setAlunoDto(alunoDto);
        matriculaDto.setTurmaDto(turmaDto);

        MatriculaModel matriculaModel = CONVERT_DTO_TO_MODEL(matriculaDto);

        matriculaRepository.save(matriculaModel);
    }

    public Set<MatriculaDto> findAllMatrilations () {
        List<MatriculaModel> MatriculaModelList = matriculaRepository.findAll();
        Set<MatriculaDto> MatriculaDtoSet = new HashSet<>();

        for(MatriculaModel matriculaModel : MatriculaModelList) {
            MatriculaDtoSet.add(CONVERT_MODEL_TO_DTO(matriculaModel));
        }

        return MatriculaDtoSet;
    }

    public MatriculaDtofindById (Integer id) {
        MatriculaModel matriculaModel = matriculaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id não encontrado"));

        return CONVERT_MODEL_TO_DTO(matriculaModel);
    }

    public void updateMatriculation (Integer id, MatriculaDto matriculaDto) {
        MatriculaDtoMatriculaDtoOld = findById(id);
        AlunoDto alunoDto = getStudent(matriculaDto);
        TurmaDto turmaDto = getClass(matriculaDto);

        if(!(matriculaDto.getTurmaId().equals(matriculaDtoOld.getTurmaId()) || matriculaDto.getAlunoId().equals(matriculaDtoOld.getAlunoId()))) {
            verifyStudentIsMatriculate(alunoDto, turmaDto);
        }

        matriculaDto.setAlunoDto(alunoDto);
        matriculaDto.setTurmaDto(turmaDto);
        matriculaDto.setId(id);

        matriculaRepository.save(CONVERT_DTO_TO_MODEL(matriculaDto));

    }

    public void delete (Integer id) {
        MatriculaDto matriculaDto= findById(id);
        matriculaRepository.delete(CONVERT_DTO_TO_MODEL(matriculaDto));
    }

    private void verifyStudentIsMatriculate(AlunoDto alunoDto, TurmaDto turmaDto) {
        AlunoModel AlunoModel = alunoService.CONVERT_DTO_TO_MODEL(alunoDto);
        TurmaModel TurmaModel = turmaService.CONVERT_DTO_TO_MODEL(turmaDto);

        Optional<MatriculaModel> optionalMatriculaModel = matriculaRepository.findByAlunoModelAndTurmaModel(AlunoModel, TurmaModel);

        if(optionalMatriculaModel.isPresent()) {
            throw new ResourceCodeAlreadyExistsException("Esse aluno já esta matriculado nesta turma");
        }
    }

    private AlunoDto getStudent (MatriculaDto matriculaDto) {
        AlunoDto alunoDto;
        try {
            alunoDto = alunoService.findById(matriculaDto.getAlunoId());
        } catch (ResourceNotFoundException ex) {
            throw new ResourceNotFoundException(ex.getMessage());
        }
        return alunoDto;
    }

    private TurmaDto getClass(MatriculaDto matriculaDto) {
        TurmaDto turmaDto;
        try {
            turmaDto = turmaService.findById(matriculaDto.getTurmaId());
        } catch (ResourceNotFoundException ex) {
            throw new ResourceNotFoundException(ex.getMessage());
        }
        return turmaDto;
    }

    public static MatriculaModel CONVERT_DTO_TO_MODEL(MatriculaDto matriculaDto) {
        MatriculaModel matriculaModel = new MatriculaModel();
        matriculaModel.setId(matriculaDto.getId() != null ? matriculaDto.getId() : null);
        matriculaModel.setAlunoModel(matriculaDto.getAlunoDto() != null ? AlunoService.CONVERT_DTO_TO_MODEL(matriculaDto.getAlunoDto()) : null);
        matriculaModel.setTurmaModel(matriculaDto.getTurmaDto() != null ? TurmaService.CONVERT_DTO_TO_MODEL(matriculaDto.getTurmaDto()) : null);

        return matriculaModel;
    }

    public static matriculaDtoCONVERT_MODEL_TO_DTO(MatriculaModel matriculaModel) {
        MatriculaDto matriculaDto= new MatriculaDto();
        matriculaDto.setId(matriculaModel.getId() != null ? matriculaModel.getId() : null);
        matriculaDto.setAlunoId(matriculaModel.getAlunoModel().getId() != null ? matriculaModel.getAlunoModel().getId() : null);
        matriculaDto.setTurmaId(matriculaModel.getTurmaModel().getId() != null ? matriculaModel.getTurmaModel().getId() : null);
        matriculaDto.setAlunoDto(matriculaModel.getAlunoModel() != null ? AlunoService.CONVERT_MODEL_TO_DTO(matriculaModel.getAlunoModel()) : null);
        matriculaDto.setTurmaDto(matriculaModel.getTurmaModel() != null ? TurmaService.CONVERT_MODEL_TO_DTO(matriculaModel.getTurmaModel()) : null);

        return matriculaDto;
    }
}
