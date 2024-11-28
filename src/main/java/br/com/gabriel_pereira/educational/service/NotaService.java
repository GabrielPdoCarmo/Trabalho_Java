package br.com.gabriel_pereira.educational.service;

import br.com.gabriel_pereira.educational.dto.*;
import br.com.gabriel_pereira.educational.model.NotaModel;
import br.com.gabriel_pereira.educational.repository.NotaRepository;
import br.com.gabriel_pereira.educational.service.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class NotaService {

    private final NotaRepository notaRepository;
    private final MatriculaService matriculaService;
    private final DisciplinaService disciplinaService;

    private final TurmaService turmaService;

    public NotaService(NotaRepository notaRepository, MatriculaService matriculaService, DisciplinaService disciplinaService, TurmaService turmaService) {
        this.notaRepository = notaRepository;
        this.matriculaService = matriculaService;
        this.disciplinaService = disciplinaService;
        this.turmaService = turmaService;
    }

    public void createdNota(NotaDto notaDto) {
        MatriculaDto matriculaDto= getMatricula(notaDto);
        DisciplinaDto DisciplinaDto = getDisciplina(notaDto);

        notaDto.setMatriculaDto(matriculaDto);
        notaDto.setDisciplinaDto(DisciplinaDto);

        NotaModel notaModel = CONVERT_DTO_TO_MODEL(notaDto);

        notaRepository.save(notaModel);
    }

    public Set<NotaDto> findAllMatriculas () {
        List<NotaModel> notaModelList = notaRepository.findAll();
        Set<NotaDto> notaDtoSet = new HashSet<>();

        for(NotaModel notaModel : notaModelList) {
            notaDtoSet.add(CONVERT_MODEL_TO_DTO(notaModel));
        }

        return notaDtoSet;
    }

    public NotaDto findById (Integer id) {
        NotaModel notaModel = notaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id não encontrado"));

        return CONVERT_MODEL_TO_DTO(notaModel);
    }

    public void updateNota (Integer id, NotaDto notaDto) {
        NotaDto notaDtoOld = findById(id);
        MatriculaDto matriculaDto= getMatricula(notaDto);
        DisciplinaDto disciplinaDto = getDisciplina(notaDto);

        notaDto.setMatriculaDto(matriculaDto);
        notaDto.setDisciplinaDto(disciplinaDto);
        notaDto.setId(id);

        notaRepository.save(CONVERT_DTO_TO_MODEL(notaDto));

    }

    public void delete (Integer id) {
        NotaDto notaDto = findById(id);
        notaRepository.delete(CONVERT_DTO_TO_MODEL(notaDto));
    }

    public Set<NotaRelatoriosDto> getNotesByAlunoId(Integer id) {
        List<NotaModel> lstNotaModel = notaRepository.findByAlunoId(id).orElseThrow(
                () -> new ResourceNotFoundException("Id not found")
        );

        Set<NotaRelatoriosDto> notaRelatoriosDtoSet = new HashSet<>();

        for(NotaModel notaModel : lstNotaModel) {
            NotaRelatoriosDto notaRelatoriosDto = new NotaRelatoriosDto();
            notaRelatoriosDto.setId(notaModel.getId());
            notaRelatoriosDto.setNota(notaModel.getNota());
            notaRelatoriosDto.setDataLancamento(notaModel.getDataLancamento());
            notaRelatoriosDto.setDisciplinaRelatoriosDto(new DisciplinaRelatoriosDto(
                    notaModel.getDisciplinaModel().getId(),
                    notaModel.getDisciplinaModel().getNome(),
                    notaModel.getDisciplinaModel().getCodigo())
            );
            notaRelatoriosDtoSet.add(notaRelatoriosDto);
        }

        return notaRelatoriosDtoSet;
    }

    public MediaTurmaDto averageNotesByClass(Integer id) {
        List<NotaModel> lstNotaModel = notaRepository.findByTurmaId(id).orElseThrow(
                () -> new ResourceNotFoundException("Id not found")
        );

        Double average = 0.0;

        for(NotaModel notaModel : lstNotaModel) {
            average += notaModel.getNota();
        }

        average = average/lstNotaModel.size();

        return new MediaTurmaDto(turmaService.findById(id), average);
    }

    public MediaDisciplinaDto averageNotesByDiscipline(Integer id) {
        List<NotaModel> lstNotaModel = notaRepository.findByDisciplinaId(id).orElseThrow(
                () -> new ResourceNotFoundException("Id not found")
        );

        Double average = 0.0;

        for(NotaModel notaModel : lstNotaModel) {
            average += notaModel.getNota();
        }

        average = average/lstNotaModel.size();

        return new MediaDisciplinaDto(disciplinaService.findById(id), average);
    }

    private MatriculaDto getMatricula(NotaDto notaDto) {
        MatriculaDto matriculaDto;
        try {
            matriculaDto= matriculaService.findById(notaDto.getMatriculaId());
        } catch (ResourceNotFoundException ex) {
            throw new ResourceNotFoundException(ex.getMessage());
        }
        return matriculaDto;
    }

    private DisciplinaDto getDisciplina(NotaDto notaDto) {
        DisciplinaDto disciplinaDto;
        try {
            disciplinaDto = disciplinaService.findById(notaDto.getDisciplinaId());
        } catch (ResourceNotFoundException ex) {
            throw new ResourceNotFoundException(ex.getMessage());
        }
        return disciplinaDto;
    }

    public static NotaModel CONVERT_DTO_TO_MODEL(NotaDto notaDto) {
        NotaModel notaModel = new NotaModel();
        notaModel.setId(notaDto.getId() != null ? notaDto.getId() : null);
        notaModel.setNota(notaDto.getNota());
        notaModel.setDataLancamento(notaDto.getDataLancamento());
        notaModel.setMatriculaModel(notaDto.getMatriculaDto() != null ? MatriculaService.CONVERT_DTO_TO_MODEL(notaDto.getMatriculaDto()) : null);
        notaModel.setDisciplinaModel(notaDto.getDisciplinaDto() != null ? DisciplinaService.CONVERT_DTO_TO_MODEL(notaDto.getDisciplinaDto()) : null);

        return notaModel;
    }

    private NotaDto CONVERT_MODEL_TO_DTO(NotaModel notaModel) {
        NotaDto notaDto = new NotaDto();
        notaDto.setId(notaModel.getId() != null ? notaModel.getId() : null);
        notaDto.setNota(notaModel.getNota());
        notaDto.setDataLancamento(notaModel.getDataLancamento());
        notaDto.setMatriculaId(notaModel.getMatriculaModel().getId() != null ? notaModel.getMatriculaModel().getId() : null);
        notaDto.setDisciplinaId(notaModel.getDisciplinaModel().getId() != null ? notaModel.getDisciplinaModel().getId() : null);
        notaDto.setMatriculaDto(notaModel.getMatriculaModel() != null ? MatriculaService.CONVERT_MODEL_TO_DTO(notaModel.getMatriculaModel()) : null);
        notaDto.setDisciplinaDto(notaModel.getDisciplinaModel() != null ? DisciplinaService.CONVERT_MODEL_TO_DTO(notaModel.getDisciplinaModel()) : null);

        return notaDto;
    }
}