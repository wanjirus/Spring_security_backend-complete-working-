package stan.inc.service.property;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import stan.inc.DTO.PropertyDTO;
import stan.inc.email.EmailSender;
import stan.inc.exceptions.ResourceNotFoundException;
import stan.inc.mapper.PropMapper;
import stan.inc.models.property.*;
import stan.inc.models.user.User;
import stan.inc.repository.CategoriesRepository;
import stan.inc.repository.PropertyRepository;
import stan.inc.repository.TypeRepository;
import stan.inc.service.auth.UserService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class PropertyService implements PropertyServiceInterface {
    private final PropertyRepository propertyRepository;
//    private final PropertyMapper propertyMapper;
    private final UserService userService;
    private final PropMapper propMapper;
    private final EmailSender emailSender;
    private final CategoriesRepository categoriesRepository;
    private final TypeRepository typeRepository;

    public Property findPropertyById(long id) throws ResourceNotFoundException {
        return propertyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Property record found with Id::" + id));
    }


    public PropertyDTO createNewProperty(long userId, PropertyDTO propertyDTO) {
        User user = userService.findUserById(userId);

        Property property = constructStoreObject(propertyDTO);

        Set<Object> strCategory = propertyDTO.getCategories();
        Set<Category> categories = new HashSet<>();

        if (strCategory == null) {
            Category constructCategory = categoriesRepository.findByName(ECategory.CONSTRUCT)
                    .orElseThrow(() -> new RuntimeException("Error: No category found with name::" + ECategory.CONSTRUCT));
            categories.add(constructCategory);
        } else {
            strCategory.forEach(category -> {
                if ("farming".equals(category)) {
                    Category farmingCategory = categoriesRepository.findByName(ECategory.FARMING)
                            .orElseThrow(() -> new RuntimeException("Error: No category found with name:"));
                    categories.add(farmingCategory);
                } else if ("estate".equals(category)) {
                    Category estateCategory = categoriesRepository.findByName(ECategory.ESTATE)
                            .orElseThrow(() -> new RuntimeException("Error: No category found "));
                    categories.add(estateCategory);
                } else if ("bill_board".equals(category)) {
                    Category B_boardCategory = categoriesRepository.findByName(ECategory.BILLBOARD)
                            .orElseThrow(() -> new RuntimeException("Error: No category found with name:"));
                    categories.add(B_boardCategory);
                } else if ("commercial".equals(category)) {
                    Category commercial_C = categoriesRepository.findByName(ECategory.COMMERCIAL)
                            .orElseThrow(() -> new RuntimeException("Error: No category found with name:"));
                    categories.add(commercial_C);
                } else {
                    Category constructCategory = categoriesRepository.findByName(ECategory.CONSTRUCT)
                            .orElseThrow(() -> new RuntimeException("Error: No category found with name "));
                    categories.add(constructCategory);
                }
            });
        }
        property.setCategories(categories);
        Set<Object> strType = propertyDTO.getTypes();
        Set<Type> types = new HashSet<>();

        if (strType == null) {
            Type typeSale = typeRepository.findByName(EType.SALE)
                    .orElseThrow(() -> new RuntimeException("Error: No category found with name::" + EType.SALE));
            types.add(typeSale);
        } else {
            strType.forEach(type -> {
                if ("sale".equals(type)) {
                    Type typeLease = typeRepository.findByName(EType.LEASE)
                            .orElseThrow(() -> new RuntimeException("Error: No category found with name:"));
                    types.add(typeLease);
                } else if ("rent".equals(type)) {
                    Type typeRent = typeRepository.findByName(EType.RENT)
                            .orElseThrow(() -> new RuntimeException("Error: No category found "));
                    types.add(typeRent);
                } else {
                    Type typeSale = typeRepository.findByName(EType.SALE)
                            .orElseThrow(() -> new RuntimeException("Error: No category found with name "));
                    types.add(typeSale);
                }
            });
        }
        property.setTypes(types);
        property.setUser(user);

        try {
            property = propertyRepository.save(property);

           //TODO: send email to user who creates property.
            emailSender.send(user.getEmail(),
                    buildEmail(user.getFirstname(), String.valueOf(property)));
        } catch (Exception e){
            throw new ResourceNotFoundException("unable to send email for property registration"+ e);
        }
//        property = propertyRepository.save(property);
        return propMapper.toPropertyDto(property);
    }

    public HashMap<String, Boolean> deleteProperty(long id) throws ResourceNotFoundException {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No staff record found with Id::" + id));
        propertyRepository.delete(property);
        HashMap<String, Boolean> response = new HashMap<>();
        response.put("record deleted successfully", Boolean.TRUE);
        return response;
    }
//
//    @Override
//    public PropertyDTO updateProperty(long id, PropertyDTO propertyDTO){
//
//        Optional<Property> property = propertyRepository.findById(id);
//        propertyDTO.setName(propertyDTO.getName());
//        property.setName(propertyDTO.getName());
//        property.setLocation(propertyDTO.getLocation());
//        property.setPhoneNumber(propertyDTO.getPhoneNumber());
//        property.setPrice(propertyDTO.getPrice());
//        property.setContactInfo(propertyDTO.getContactInfo());
//        property.setDescription(propertyDTO.getDescription());
//        Property updateProperty = propertyRepository.save(property);
//        return propertyMapper.toPropertyDto(updateProperty);
//
//    }
//    public ResponseEntity<HashMap<String, Boolean>> deleteProperty (long id){
//        Optional<Property> property = propertyRepository.findById(id);
//        propertyRepository.delete(property);
//        HashMap<String, Boolean> response = new HashMap<>();
//        response.put("deleted", Boolean.TRUE);
//        return ResponseEntity.ok(response);
//    }


    public List<PropertyDTO> findAll() {
        List<Property> allProperties = propertyRepository.findAll();
        return propMapper.toPropertyDtoList(allProperties);
    }

    @Override
    public List<PropertyDTO> findPropertyByUserId(long userId) {
        List<Property> propertyList = propertyRepository.findByUserId(userId);
        return propMapper.toPropertyDtoList(propertyList);

    }

//    @Override
//    public List<PropertyDTO> findPropertyByType(Long typeId) {
//        Optional<Type> type = typeRepository.findByName(EType.SALE);
//        List<Property> propertyList = propertyRepository.findByTypeSale(type);
//        return propertyMapper.toPropertyDto(propertyList);
//    }


//    @Override
//    public List<PropertyDTO> findPropertyByType(long typeId) {
//        List<Property> propertyList = propertyRepository.findByUserId(userId);
//        return null;
//    }



    @Override
    public Boolean existByUserId(Long UserId) {
        User user = userService.findUserById(UserId);
        boolean property = propertyRepository.existsPropertiesByUser(user);
        return property;
//        return new ResponseEntity<>(staffs, HttpStatus.OK);
    }


    //
    private Property constructStoreObject(PropertyDTO propertyDTO) {
        return Property.builder().name(propertyDTO.getName())
                .location(propertyDTO.getLocation())
                .price(propertyDTO.getPrice())
                .status(propertyDTO.getStatus())
                .dimensions(propertyDTO.getDimensions())
//                .phoneNumber(propertyDTO.getPhoneNumber())
                .contactInfo(propertyDTO.getContactInfo())

                .description(propertyDTO.getDescription())
                .build();
    }

//    @Transactional
//    public Property findPropertyById(long id) throws ResourceNotFoundException {
//        return propertyRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("No Property record Found::"+id));
//    }
private String buildEmail(String name, String link) {
    return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
            "\n" +
            "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
            "\n" +
            "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
            "    <tbody><tr>\n" +
            "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
            "        \n" +
            "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
            "          <tbody><tr>\n" +
            "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
            "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
            "                  <tbody><tr>\n" +
            "                    <td style=\"padding-left:10px\">\n" +
            "                  \n" +
            "                    </td>\n" +
            "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
            "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
            "                    </td>\n" +
            "                  </tr>\n" +
            "                </tbody></table>\n" +
            "              </a>\n" +
            "            </td>\n" +
            "          </tr>\n" +
            "        </tbody></table>\n" +
            "        \n" +
            "      </td>\n" +
            "    </tr>\n" +
            "  </tbody></table>\n" +
            "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
            "    <tbody><tr>\n" +
            "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
            "      <td>\n" +
            "        \n" +
            "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
            "                  <tbody><tr>\n" +
            "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
            "                  </tr>\n" +
            "                </tbody></table>\n" +
            "        \n" +
            "      </td>\n" +
            "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
            "    </tr>\n" +
            "  </tbody></table>\n" +
            "\n" +
            "\n" +
            "\n" +
            "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
            "    <tbody><tr>\n" +
            "      <td height=\"30\"><br></td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
            "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
            "        \n" +
            "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
            "        \n" +
            "      </td>\n" +
            "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "      <td height=\"30\"><br></td>\n" +
            "    </tr>\n" +
            "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
            "\n" +
            "</div></div>";
}
}
