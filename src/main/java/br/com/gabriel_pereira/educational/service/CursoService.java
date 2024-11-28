package br.com.gabriel_pereira.educational.service;

import br.com.gabriel_pereira.educational.dto.CursoDto;
import br.com.gabriel_pereira.educational.model.CursoModel;
import br.com.gabriel_pereira.educational.model.AlunoModel;
import br.com.gabriel_pereira.educational.repository.CursoRepository;
import br.com.gabriel_pereira.educational.service.exception.ResourceCodeAlreadyExistsException;
import br.com.gabriel_pereira.educational.service.exception.ResourceEmailAlreadyExistsException;
import br.com.gabriel_pereira.educational.service.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public void createCurso(CursoDto cursoDto) {

        verifyCodigo(cursoDto);

        CursoModel cursoModel = CONVERT_DTO_TO_MODEL(cursoDto);

        cursoRepository.save(cursoModel);
    }

    public void updateCurso(CursoDto cursoDto, Integer id) {
        CursoDto verifyCursoDto = findById(id);

        if(!verifyCursoDto.getCodigo().equals(cursoDto.getCodigo())) {
            verifyCodigo(cursoDto);
        }
        
        CursoModel cursoModel = CONVERT_DTO_TO_MODEL(cursoDto);
        cursoModel.setId(id);
        cursoRepository.save(cursoModel);
    }

    public CursoDto findById (Integer id) {
        CursoModel cursoModel = cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id não foi encontrado"));

        return CONVERT_MODEL_TO_DTO(cursoModel);
    }

    public List<CursoDto> findAll () {
        List<CursoModel> lstCursoModel = cursoRepository.findAll();
        List<CursoDto> lstCursoDto = new ArrayList<>();

        for(CursoModel cursoModel : lstCursoModel) {
            lstCursoDto.add(CONVERT_MODEL_TO_DTO(cursoModel));
        }

        return lstCursoDto;
    }

    public void deleteCurso(Integer id) {
        CursoDto verifyCursoDto = findById(id);
        CursoModel cursoModel = CONVERT_DTO_TO_MODEL(verifyCursoDto);
        cursoModel.setId(id);
        cursoRepository.delete(CursoModel);
    }

    private void verifyCodigo (CursoDto cursoDto) {
        Optional<CursoModel> cursoModelOptional = cursoRepository.findByCodigo(cursoDto.getCodigo());

        if(cursoModelOptional.isPresent()) {
            throw new ResourceCodeAlreadyExistsException("Já existe um curso com esse código");
        }
    }

    public static CursoModel CONVERT_DTO_TO_MODEL(CursoDto cursoDto) {
        CursoModel cursoDto = new CursoModel();
        cursoModel.setId(cursoDto.getId() != null ? cursoDto.getId() : null);
        cursoModel.setNome(cursoDto.getNome());
        cursoModel.setCodigo(cursoDto.getCodigo());
        cursoModel.setCargaHoraria(cursoDto.getCargaHoraria());

        return cursoModel;
    }

    public static CursoDto CONVERT_MODEL_TO_DTO(CursoModel cursoModel) {
        CursoDto cursoDto = new CursoDto();
        cursoDto.setId(cursoModel.getId() != null ? cursoModel.getId() : null);
        cursoDto.setNome(cursoModel.getNome());
        cursoDto.setCodigo(cursoModel.getCodigo());
        cursoDto.setCargaHoraria(cursoModel.getCargaHoraria());

        return cursoDto;
    }

}
