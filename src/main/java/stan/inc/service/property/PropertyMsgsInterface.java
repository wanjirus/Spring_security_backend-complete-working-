package stan.inc.service.property;//package stan.security.spring_security.services.property;

import stan.inc.DTO.MsgsDTO;

import java.util.List;

public interface PropertyMsgsInterface {
     MsgsDTO createPropertyMessage(long propertyId, MsgsDTO msgsDTO);

     List<MsgsDTO> findAll();
     List<MsgsDTO> findPropertyMessageByPropertyId(Long propertyId);
}
