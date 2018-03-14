package com.greenfox.kalendaryo.models.event;

import com.google.api.client.util.DateTime;

import java.util.List;

/**
 * Created by Lilla on 2018. 03. 14..
 */

public class EventsResource {

    String kind;
    String etag;
    String id;
    String status;
    String htmlLink;
    DateTime created;
    DateTime updated;
    String summary;
    String description;
    String location;
    String colorId;
    /**GoogleAuth creator {
        String id;
        String email;
        String displayName;
        boolean self;
    }*/
    /**GoogleAuth organizer {
     String id;
     String email;
     String displayName;
     boolean self;
     }*/
    /** ObjectDataValami start {
     * Data data;
     * DataTime dataTime;
     * String timeZone;
     }*/
    /** ObjectDataValami start {
     * Data data;
     * DataTime dataTime;
     * String timeZone;
     }*/
    boolean endTimeUnspecified;
    List<String> recurrence;
    String recurringEventId;
    /** ObjectDataValami originalStartTime {
     * Data data;
     * DataTime dataTime;
     * String timeZone;
     }*/
    String transparency;
    String visibility;
    String iCalUID;
    int sequence;
    /**List<People> attendees {
     String id;
     String email;
     String displayName;
     boolean self;
     boolean resource;
     boolean optional;
     String comment;
     int additionalGuests;
     }*/
     boolean attendeesOmitted;
     /** ObjectProperty extendedProperties {
        "private": {
            (key): string
        },
        "shared": {
            (key): string
        }
        }*/
      String hangoutLink;
    /** ObjectProperty conferenceData {
        "createRequest": {
            "requestId": string,
                    "conferenceSolutionKey": {
                "type": string
            },
            "status": {
                "statusCode": string
            }
     }*/
    /**Lis<OjectPoints>" entryPoints": [
        {
            "entryPointType": string,
                "uri": string,
                "label": string,
                "pin": string,
                "accessCode": string,
                "meetingCode": string,
                "passcode": string,
                "password": string
        }
    ]*/
     /** ObjectSolution conferenceSolution {
            "key": {
                "type": string
            },
            "name": string,
                    "iconUri": string
        },
        "conferenceId": string,
                "signature": string,
                "notes": string
    }*/
    /** ObjectGadget gadget {
        String type;
        String title;
        String link;
        String iconLink;
        int width;
        int height;
        String display;
        ObjectPref preferences {
            (key): string
        }
    }*/
    boolean anyoneCanAddSelf;
    boolean guestsCanInviteOthers;
    boolean guestsCanModify;
    boolean guestsCanSeeOtherGuests;
    boolean privateCopy;
    boolean locked;
    /** List<ObjectReminder> Reminder reminders {
        boolean useDefault;
     List<ObjectOverride>overrides [
        {
            "method": string,
                "minutes": integer
        }
    ]
    }*/
    /** ObjectSource source {
        "url": string,
        "title": string
    }*/
    /**List <ObjectAttachement> attachments [
    {
        "fileUrl": string,
        "title": string,
        "mimeType": string,
        "iconLink": string,
        "fileId": string
    }
  ]*/
}
