package br.com.gabriel_pereira.educational.service;

import br.com.gabriel_pereira.educational.dto.AlunoDto;
import br.com.gabriel_pereira.educational.model.AlunoModel;
import br.com.gabriel_pereira.educational.repository.AlunoRepository;
import br.com.gabriel_pereira.educational.service.exception.ResourceEmailAlreadyExistsException;
import br.com.gabriel_pereira.educational.service.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public void createAluno(AlunoDto alunoDto) {
        verifyEmail(alunoDto);

        AlunoModel alunoModel = CONVERT_DTO_TO_MODEL(alunoDto);

        alunoRepository.save(alunoModel);
    }

    public void updateAluno(AlunoDto alunoDto, Integer id) {
        AlunoDto verifyAlunoDto = findById(id);

        if(!verifyAlunoDto.getEmail().equals(alunoDto.getEmail())){
            verifyEmail(alunoDto);
        }

        AlunoModel alunoModel = CONVERT_DTO_TO_MODEL(alunoDto);
        alunoModel.setId(id);
        alunoRepository.save(alunoModel);
    }

    public AlunoDto findById (Integer id) {
        AlunoModel alunoModel = alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id não foi encontrado"));

        return CONVERT_MODEL_TO_DTO(alunoModel);
    }

    public List<AlunoDto> findAll () {
        List<AlunoModel> lstAlunoModel = alunoRepository.findAll();
        List<AlunoDto> lstAlunoDto = new ArrayList<>();

        for(AlunoModel alunoModel : lstAlunoModel) {
            lstAlunoDto.add(CONVERT_MODEL_TO_DTO(alunoModel));
        }

        return lstAlunoDto;
    }

    public void deleteAluno(Integer id) {
        AlunoDto verifyAlunoDto = findById(id);
        AlunoModel alunoModel = CONVERT_DTO_TO_MODEL(verifyAlunoDto);
        alunoModel.setId(id);
        alunoRepository.delete(alunoModel);
    }

    private void verifyEmail (AlunoDto alunoDto) {
        Optional<AlunoModel> optionalAlunoModel = alunoRepository.findByEmail(alunoDto.getEmail());

        if(optionalAlunoModel.isPresent()){
            throw new ResourceEmailAlreadyExistsException("Já existe um aluno com teste email");
        }
    }

    public static AlunoModel CONVERT_DTO_TO_MODEL(AlunoDto alunoDto) {
        AlunoModel alunoModel = new AlunoModel();
        alunoModel.setId(alunoDto.getId() != null ? alunoDto.getId() : null);
        alunoModel.setNome(alunoDto.getNome());
        alunoModel.setEmail(alunoDto.getEmail());
        alunoModel.setMatricula(alunoDto.getMatricula());
        alunoModel.setDataNascimento(alunoDto.getDataNascimento());

        return alunoModel;
    }

    public static AlunoDto CONVERT_MODEL_TO_DTO (AlunoModel alunoModel) {
        AlunoDto alunoDto = new AlunoDto();
        alunoDto.setId(alunoModel.getId() != null ? alunoModel.getId() : null);
        alunoDto.setNome(alunoModel.getNome());
        alunoDto.setEmail(alunoModel.getEmail());
        alunoDto.setMatricula(alunoModel.getMatricula());
        alunoDto.setDataNascimento(alunoModel.getDataNascimento());

        return alunoDto;
    }

}
