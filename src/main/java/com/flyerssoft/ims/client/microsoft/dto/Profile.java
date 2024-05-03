package com.flyerssoft.ims.client.microsoft.dto;

import java.util.List;

/**
 * Transfer object representing employee profile response
 * from microsoft graph apis.
 *
 * @param businessPhones The business phones associated with the profile.
 *
 *  @param displayName The display name of the profile.
 *
 *  @param givenName The given name of the profile.
 *
 *  @param jobTitle The job title of the profile.
 *
 *  @param mail The email address of the profile.
 *
 *  @param mobilePhone The mobile phone number of the profile.
 *
 *  @param officeLocation The office location of the profile.
 *
 *  @param preferredLanguage The preferred language of the profile.
 *
 *  @param surname The surname of the profile.
 *
 *  @param userPrincipalName The user principal name of the profile.
 *
 *  @param id The ID of the profile.
 */
public record Profile(

    List<String> businessPhones,

    String displayName,

    String givenName,

    String jobTitle,

    String mail,

    String mobilePhone,

    String officeLocation,

    String preferredLanguage,

    String surname,

    String userPrincipalName,

    String id
) {
}
