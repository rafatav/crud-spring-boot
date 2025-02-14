package com.group.crud_client.services;

import com.group.crud_client.dto.ClientDTO;
import com.group.crud_client.entities.Client;
import com.group.crud_client.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Optional<Client> result = repository.findById(id);
        Client client = result.get();
        ClientDTO dto = new ClientDTO(client);
        return dto;
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll (Pageable pageable) {
        Page<Client> result = repository.findAll(pageable);
        Page<ClientDTO> dto = result.map(x -> new ClientDTO(x));
        return dto;
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto) {
        Client entity = new Client();
        saveDTOtoEntity(dto, entity);
        entity = repository.save(entity);
        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto) {
        Client entity = repository.findById(id).get();
        saveDTOtoEntity(dto, entity);
        entity = repository.save(entity);
        return new ClientDTO(entity);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        Optional<Client> result = repository.findById(id);
        Client client = result.get();
        repository.delete(client);
    }

    private void saveDTOtoEntity(ClientDTO dto, Client entity) {
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }
}
