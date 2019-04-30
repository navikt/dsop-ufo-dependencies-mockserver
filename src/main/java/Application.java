
import no.nav.tokentest.TokenHandler;
import org.junit.AfterClass;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.JsonBody;

import static java.lang.System.lineSeparator;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.Header.header;
import static org.mockserver.model.HttpResponse.response;

public class Application {
    private static ClientAndServer mockServer;
    private static TokenHandler tokenHandler = new TokenHandler();

    public static void main(String[] args) {
        startMockServer();
    }

    private static void startMockServer() {
        mockServer = startClientAndServer(1090);
        mockServer.reset();

        createExpectationForUforedataMedToBeregningsperioder();

        createExpectionForUforedataKlagePaaAvslag();

        createExpectionForUforedataIkkeSokt();

        createExpectationForUforedataUnderBehandling();

        createExpectationForUforedataUtvandringEksport();

        mockServer.when(HttpRequest.request()
                .withMethod("GET")
                .withPath("/sts/token"))
                .respond(
                        response()
                                .withBody(new JsonBody("") {
                                    @Override
                                    public String getValue() {
                                        String signedToken = tokenHandler.getSignedToken("");
                                        return "{\n" +
                                                "    \"access_token\": \"" + signedToken + "\",\n" +
                                                "    \"token_type\": \"Bearer\",\n" +
                                                "    \"expires_in\": 3600\n" +
                                                "}";
                                    }
                                }));
    }

    private void stopMockServer() {
        mockServer.stop();
    }

    private static void createExpectationForUforedataMedToBeregningsperioder() {
        mockServer.when(HttpRequest.request()
                .withMethod("POST")
                .withPath("/pensjon-ws/api/pensjonsinformasjon/v1/fnr/14098730754")
                .withBody(requestBody()))
                .respond(
                        response()
                                .withHeaders(header("Accept", "application/xml"))
                                .withStatusCode(200)
                                .withBody("<ns33:pensjonsinformasjon xmlns:ns10=\"http://nav.no/pensjon/v1/trygdetid\" xmlns:ns11=\"http://nav.no/pensjon/v1/trygdetidListe\" xmlns:ns12=\"http://nav.no/pensjon/v1/inngangOgEksport\" xmlns:ns13=\"http://nav.no/pensjon/v1/trygdetidAvdodListe\" xmlns:ns14=\"http://nav.no/pensjon/v1/trygdeavtale\" xmlns:ns15=\"http://nav.no/pensjon/v1/trygdetidAvdodFarListe\" xmlns:ns16=\"http://nav.no/pensjon/v1/trygdetidAvdodMorListe\" xmlns:ns17=\"http://nav.no/pensjon/v1/vilkarsvurdering\" xmlns:ns18=\"http://nav.no/pensjon/v1/vilkarsvurderingUforetrygd\" xmlns:ns19=\"http://nav.no/pensjon/v1/vilkarsvurderingMedlemstid\" xmlns:ns2=\"http://nav.no/pensjon/v1/person\" xmlns:ns20=\"http://nav.no/pensjon/v1/vilkarsvurderingListe\" xmlns:ns21=\"http://nav.no/pensjon/v1/avdod\" xmlns:ns22=\"http://nav.no/pensjon/v1/avdodesYtelse\" xmlns:ns23=\"http://nav.no/pensjon/v1/avdodesYtelseListe\" xmlns:ns24=\"http://nav.no/pensjon/v1/brukersBarn\" xmlns:ns25=\"http://nav.no/pensjon/v1/brukersBarnListe\" xmlns:ns26=\"http://nav.no/pensjon/v1/KravHistorikk\" xmlns:ns27=\"http://nav.no/pensjon/v1/kravHistorikkListe\" xmlns:ns28=\"http://nav.no/pensjon/v1/sak\" xmlns:ns29=\"http://nav.no/pensjon/v1/brukersSakerListe\" xmlns:ns3=\"http://nav.no/pensjon/v1/ektefellePartnerSamboer\" xmlns:ns30=\"http://nav.no/pensjon/v1/uforeopplysninger\" xmlns:ns31=\"http://nav.no/pensjon/v1/beregningsperiode\" xmlns:ns32=\"http://nav.no/pensjon/v1/beregningsperiodeList\" xmlns:ns33=\"http://nav.no/pensjon/v1/pensjonsinformasjon\" xmlns:ns4=\"http://nav.no/pensjon/v1/ektefellePartnerSamboerListe\" xmlns:ns5=\"http://nav.no/pensjon/v1/ytelsePerMaaned\" xmlns:ns6=\"http://nav.no/pensjon/v1/ytelseskomponent\" xmlns:ns7=\"http://nav.no/pensjon/v1/ytelsePerMaanedListe\" xmlns:ns8=\"http://nav.no/pensjon/v1/vedtak\" xmlns:ns9=\"http://nav.no/pensjon/v1/sakAlder\">\n" +
                                        "    <ns33:uforeopplysninger xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns30:v1.Uforeopplysninger\">\n" +
                                        "        <ns30:sakStatus>LOPENDE</ns30:sakStatus>\n" +
                                        "        <ns30:forsteVirkningspunkt>2018-01-01T00:00:00+01:00</ns30:forsteVirkningspunkt>\n" +
                                        "        <ns30:beregningsperioderListe>\n" +
                                        "            <ns32:beregningsperiode>\n" +
                                        "                <ns31:fom>2018-01-01T00:00:00+01:00</ns31:fom>\n" +
                                        "                <ns31:tom>2019-04-30T00:00:00+02:00</ns31:tom>\n" +
                                        "                <ns31:uforetidspunkt>2017-01-01T12:00:00+01:00</ns31:uforetidspunkt>\n" +
                                        "                <ns31:uforegrad>100</ns31:uforegrad>\n" +
                                        "                <ns31:ifu>416592</ns31:ifu>\n" +
                                        "                <ns31:inntektBruktIAvkortingen>0</ns31:inntektBruktIAvkortingen>\n" +
                                        "                <ns31:ferdiglignetInntekt>false</ns31:ferdiglignetInntekt>\n" +
                                        "                <ns31:klagebehandles>false</ns31:klagebehandles>\n" +
                                        "            </ns32:beregningsperiode>\n" +
                                        "            <ns32:beregningsperiode>\n" +
                                        "                <ns31:fom>2019-05-01T00:00:00+02:00</ns31:fom>\n" +
                                        "                <ns31:uforetidspunkt>2017-01-01T12:00:00+01:00</ns31:uforetidspunkt>\n" +
                                        "                <ns31:uforegrad>100</ns31:uforegrad>\n" +
                                        "                <ns31:ifu>416592</ns31:ifu>\n" +
                                        "                <ns31:inntektBruktIAvkortingen>150000</ns31:inntektBruktIAvkortingen>\n" +
                                        "                <ns31:ferdiglignetInntekt>false</ns31:ferdiglignetInntekt>\n" +
                                        "                <ns31:klagebehandles>false</ns31:klagebehandles>\n" +
                                        "            </ns32:beregningsperiode>\n" +
                                        "        </ns30:beregningsperioderListe>\n" +
                                        "    </ns33:uforeopplysninger>\n" +
                                        "</ns33:pensjonsinformasjon>")

                );
    }

    private static void createExpectionForUforedataKlagePaaAvslag() {
        mockServer.when(HttpRequest.request()
                .withMethod("POST")
                .withPath("/pensjon-ws/api/pensjonsinformasjon/v1/fnr/17096749414")
                .withBody(requestBody()))
                .respond(
                        response()
                                .withHeaders(header("Accept", "application/xml"))
                                .withStatusCode(200)
                                .withBody("<ns33:pensjonsinformasjon xmlns:ns10=\"http://nav.no/pensjon/v1/trygdetid\" xmlns:ns11=\"http://nav.no/pensjon/v1/trygdetidListe\" xmlns:ns12=\"http://nav.no/pensjon/v1/inngangOgEksport\" xmlns:ns13=\"http://nav.no/pensjon/v1/trygdetidAvdodListe\" xmlns:ns14=\"http://nav.no/pensjon/v1/trygdeavtale\" xmlns:ns15=\"http://nav.no/pensjon/v1/trygdetidAvdodFarListe\" xmlns:ns16=\"http://nav.no/pensjon/v1/trygdetidAvdodMorListe\" xmlns:ns17=\"http://nav.no/pensjon/v1/vilkarsvurdering\" xmlns:ns18=\"http://nav.no/pensjon/v1/vilkarsvurderingUforetrygd\" xmlns:ns19=\"http://nav.no/pensjon/v1/vilkarsvurderingMedlemstid\" xmlns:ns2=\"http://nav.no/pensjon/v1/person\" xmlns:ns20=\"http://nav.no/pensjon/v1/vilkarsvurderingListe\" xmlns:ns21=\"http://nav.no/pensjon/v1/avdod\" xmlns:ns22=\"http://nav.no/pensjon/v1/avdodesYtelse\" xmlns:ns23=\"http://nav.no/pensjon/v1/avdodesYtelseListe\" xmlns:ns24=\"http://nav.no/pensjon/v1/brukersBarn\" xmlns:ns25=\"http://nav.no/pensjon/v1/brukersBarnListe\" xmlns:ns26=\"http://nav.no/pensjon/v1/KravHistorikk\" xmlns:ns27=\"http://nav.no/pensjon/v1/kravHistorikkListe\" xmlns:ns28=\"http://nav.no/pensjon/v1/sak\" xmlns:ns29=\"http://nav.no/pensjon/v1/brukersSakerListe\" xmlns:ns3=\"http://nav.no/pensjon/v1/ektefellePartnerSamboer\" xmlns:ns30=\"http://nav.no/pensjon/v1/uforeopplysninger\" xmlns:ns31=\"http://nav.no/pensjon/v1/beregningsperiode\" xmlns:ns32=\"http://nav.no/pensjon/v1/beregningsperiodeList\" xmlns:ns33=\"http://nav.no/pensjon/v1/pensjonsinformasjon\" xmlns:ns4=\"http://nav.no/pensjon/v1/ektefellePartnerSamboerListe\" xmlns:ns5=\"http://nav.no/pensjon/v1/ytelsePerMaaned\" xmlns:ns6=\"http://nav.no/pensjon/v1/ytelseskomponent\" xmlns:ns7=\"http://nav.no/pensjon/v1/ytelsePerMaanedListe\" xmlns:ns8=\"http://nav.no/pensjon/v1/vedtak\" xmlns:ns9=\"http://nav.no/pensjon/v1/sakAlder\">\n" +
                                        "    <ns33:uforeopplysninger xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns30:v1.Uforeopplysninger\">\n" +
                                        "        <ns30:sakStatus>KLAGE_PA_AVSLAG</ns30:sakStatus>\n" +
                                        "        <ns30:beregningsperioderListe></ns30:beregningsperioderListe>\n" +
                                        "    </ns33:uforeopplysninger>\n" +
                                        "</ns33:pensjonsinformasjon>")
                );
    }

    private static void createExpectionForUforedataIkkeSokt() {
        mockServer.when(HttpRequest.request()
                .withMethod("POST")
                .withPath("/pensjon-ws/api/pensjonsinformasjon/v1/fnr/27025221635")
                .withBody(requestBody()))
                .respond(
                        response()
                                .withHeaders(header("Accept", "application/xml"))
                                .withStatusCode(200)
                                .withBody("<ns33:pensjonsinformasjon xmlns:ns10=\"http://nav.no/pensjon/v1/trygdetid\" xmlns:ns11=\"http://nav.no/pensjon/v1/trygdetidListe\" xmlns:ns12=\"http://nav.no/pensjon/v1/inngangOgEksport\" xmlns:ns13=\"http://nav.no/pensjon/v1/trygdetidAvdodListe\" xmlns:ns14=\"http://nav.no/pensjon/v1/trygdeavtale\" xmlns:ns15=\"http://nav.no/pensjon/v1/trygdetidAvdodFarListe\" xmlns:ns16=\"http://nav.no/pensjon/v1/trygdetidAvdodMorListe\" xmlns:ns17=\"http://nav.no/pensjon/v1/vilkarsvurdering\" xmlns:ns18=\"http://nav.no/pensjon/v1/vilkarsvurderingUforetrygd\" xmlns:ns19=\"http://nav.no/pensjon/v1/vilkarsvurderingMedlemstid\" xmlns:ns2=\"http://nav.no/pensjon/v1/person\" xmlns:ns20=\"http://nav.no/pensjon/v1/vilkarsvurderingListe\" xmlns:ns21=\"http://nav.no/pensjon/v1/avdod\" xmlns:ns22=\"http://nav.no/pensjon/v1/avdodesYtelse\" xmlns:ns23=\"http://nav.no/pensjon/v1/avdodesYtelseListe\" xmlns:ns24=\"http://nav.no/pensjon/v1/brukersBarn\" xmlns:ns25=\"http://nav.no/pensjon/v1/brukersBarnListe\" xmlns:ns26=\"http://nav.no/pensjon/v1/KravHistorikk\" xmlns:ns27=\"http://nav.no/pensjon/v1/kravHistorikkListe\" xmlns:ns28=\"http://nav.no/pensjon/v1/sak\" xmlns:ns29=\"http://nav.no/pensjon/v1/brukersSakerListe\" xmlns:ns3=\"http://nav.no/pensjon/v1/ektefellePartnerSamboer\" xmlns:ns30=\"http://nav.no/pensjon/v1/uforeopplysninger\" xmlns:ns31=\"http://nav.no/pensjon/v1/beregningsperiode\" xmlns:ns32=\"http://nav.no/pensjon/v1/beregningsperiodeList\" xmlns:ns33=\"http://nav.no/pensjon/v1/pensjonsinformasjon\" xmlns:ns4=\"http://nav.no/pensjon/v1/ektefellePartnerSamboerListe\" xmlns:ns5=\"http://nav.no/pensjon/v1/ytelsePerMaaned\" xmlns:ns6=\"http://nav.no/pensjon/v1/ytelseskomponent\" xmlns:ns7=\"http://nav.no/pensjon/v1/ytelsePerMaanedListe\" xmlns:ns8=\"http://nav.no/pensjon/v1/vedtak\" xmlns:ns9=\"http://nav.no/pensjon/v1/sakAlder\">\n" +
                                        "    <ns33:uforeopplysninger xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns30:v1.Uforeopplysninger\">\n" +
                                        "        <ns30:sakStatus>IKKE_SOKT</ns30:sakStatus>\n" +
                                        "        <ns30:beregningsperioderListe></ns30:beregningsperioderListe>\n" +
                                        "    </ns33:uforeopplysninger>\n" +
                                        "</ns33:pensjonsinformasjon>")
                );

    }

    private static void createExpectationForUforedataUnderBehandling() {
        mockServer.when(HttpRequest.request()
                .withMethod("POST")
                .withPath("/pensjon-ws/api/pensjonsinformasjon/v1/fnr/17025224116")
                .withBody(requestBody()))
                .respond(
                        response()
                                .withHeaders(header("Accept", "application/xml"))
                                .withStatusCode(200)
                                .withBody("<ns33:pensjonsinformasjon xmlns:ns10=\"http://nav.no/pensjon/v1/trygdetid\" xmlns:ns11=\"http://nav.no/pensjon/v1/trygdetidListe\" xmlns:ns12=\"http://nav.no/pensjon/v1/inngangOgEksport\" xmlns:ns13=\"http://nav.no/pensjon/v1/trygdetidAvdodListe\" xmlns:ns14=\"http://nav.no/pensjon/v1/trygdeavtale\" xmlns:ns15=\"http://nav.no/pensjon/v1/trygdetidAvdodFarListe\" xmlns:ns16=\"http://nav.no/pensjon/v1/trygdetidAvdodMorListe\" xmlns:ns17=\"http://nav.no/pensjon/v1/vilkarsvurdering\" xmlns:ns18=\"http://nav.no/pensjon/v1/vilkarsvurderingUforetrygd\" xmlns:ns19=\"http://nav.no/pensjon/v1/vilkarsvurderingMedlemstid\" xmlns:ns2=\"http://nav.no/pensjon/v1/person\" xmlns:ns20=\"http://nav.no/pensjon/v1/vilkarsvurderingListe\" xmlns:ns21=\"http://nav.no/pensjon/v1/avdod\" xmlns:ns22=\"http://nav.no/pensjon/v1/avdodesYtelse\" xmlns:ns23=\"http://nav.no/pensjon/v1/avdodesYtelseListe\" xmlns:ns24=\"http://nav.no/pensjon/v1/brukersBarn\" xmlns:ns25=\"http://nav.no/pensjon/v1/brukersBarnListe\" xmlns:ns26=\"http://nav.no/pensjon/v1/KravHistorikk\" xmlns:ns27=\"http://nav.no/pensjon/v1/kravHistorikkListe\" xmlns:ns28=\"http://nav.no/pensjon/v1/sak\" xmlns:ns29=\"http://nav.no/pensjon/v1/brukersSakerListe\" xmlns:ns3=\"http://nav.no/pensjon/v1/ektefellePartnerSamboer\" xmlns:ns30=\"http://nav.no/pensjon/v1/uforeopplysninger\" xmlns:ns31=\"http://nav.no/pensjon/v1/beregningsperiode\" xmlns:ns32=\"http://nav.no/pensjon/v1/beregningsperiodeList\" xmlns:ns33=\"http://nav.no/pensjon/v1/pensjonsinformasjon\" xmlns:ns4=\"http://nav.no/pensjon/v1/ektefellePartnerSamboerListe\" xmlns:ns5=\"http://nav.no/pensjon/v1/ytelsePerMaaned\" xmlns:ns6=\"http://nav.no/pensjon/v1/ytelseskomponent\" xmlns:ns7=\"http://nav.no/pensjon/v1/ytelsePerMaanedListe\" xmlns:ns8=\"http://nav.no/pensjon/v1/vedtak\" xmlns:ns9=\"http://nav.no/pensjon/v1/sakAlder\">\n" +
                                        "    <ns33:uforeopplysninger xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns30:v1.Uforeopplysninger\">\n" +
                                        "        <ns30:sakStatus>UNDER_BEHANDLING</ns30:sakStatus>\n" +
                                        "        <ns30:beregningsperioderListe></ns30:beregningsperioderListe>\n" +
                                        "    </ns33:uforeopplysninger>\n" +
                                        "</ns33:pensjonsinformasjon>")

                );
    }

    private static void createExpectationForUforedataUtvandringEksport() {
        mockServer.when(HttpRequest.request()
                .withMethod("POST")
                .withPath("/pensjon-ws/api/pensjonsinformasjon/v1/fnr/26097022525")
                .withBody(requestBody()))
                .respond(response()
                        .withHeaders(header("Accept", "application/xml"))
                        .withStatusCode(200)
                        .withBody("<ns33:pensjonsinformasjon xmlns:ns10=\"http://nav.no/pensjon/v1/trygdetid\" xmlns:ns11=\"http://nav.no/pensjon/v1/trygdetidListe\" xmlns:ns12=\"http://nav.no/pensjon/v1/inngangOgEksport\" xmlns:ns13=\"http://nav.no/pensjon/v1/trygdetidAvdodListe\" xmlns:ns14=\"http://nav.no/pensjon/v1/trygdeavtale\" xmlns:ns15=\"http://nav.no/pensjon/v1/trygdetidAvdodFarListe\" xmlns:ns16=\"http://nav.no/pensjon/v1/trygdetidAvdodMorListe\" xmlns:ns17=\"http://nav.no/pensjon/v1/vilkarsvurdering\" xmlns:ns18=\"http://nav.no/pensjon/v1/vilkarsvurderingUforetrygd\" xmlns:ns19=\"http://nav.no/pensjon/v1/vilkarsvurderingMedlemstid\" xmlns:ns2=\"http://nav.no/pensjon/v1/person\" xmlns:ns20=\"http://nav.no/pensjon/v1/vilkarsvurderingListe\" xmlns:ns21=\"http://nav.no/pensjon/v1/avdod\" xmlns:ns22=\"http://nav.no/pensjon/v1/avdodesYtelse\" xmlns:ns23=\"http://nav.no/pensjon/v1/avdodesYtelseListe\" xmlns:ns24=\"http://nav.no/pensjon/v1/brukersBarn\" xmlns:ns25=\"http://nav.no/pensjon/v1/brukersBarnListe\" xmlns:ns26=\"http://nav.no/pensjon/v1/KravHistorikk\" xmlns:ns27=\"http://nav.no/pensjon/v1/kravHistorikkListe\" xmlns:ns28=\"http://nav.no/pensjon/v1/sak\" xmlns:ns29=\"http://nav.no/pensjon/v1/brukersSakerListe\" xmlns:ns3=\"http://nav.no/pensjon/v1/ektefellePartnerSamboer\" xmlns:ns30=\"http://nav.no/pensjon/v1/uforeopplysninger\" xmlns:ns31=\"http://nav.no/pensjon/v1/beregningsperiode\" xmlns:ns32=\"http://nav.no/pensjon/v1/beregningsperiodeList\" xmlns:ns33=\"http://nav.no/pensjon/v1/pensjonsinformasjon\" xmlns:ns4=\"http://nav.no/pensjon/v1/ektefellePartnerSamboerListe\" xmlns:ns5=\"http://nav.no/pensjon/v1/ytelsePerMaaned\" xmlns:ns6=\"http://nav.no/pensjon/v1/ytelseskomponent\" xmlns:ns7=\"http://nav.no/pensjon/v1/ytelsePerMaanedListe\" xmlns:ns8=\"http://nav.no/pensjon/v1/vedtak\" xmlns:ns9=\"http://nav.no/pensjon/v1/sakAlder\">\n" +
                                "    <ns33:uforeopplysninger xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns30:v1.Uforeopplysninger\">\n" +
                                "        <ns30:sakStatus>EKSPORT</ns30:sakStatus>\n" +
                                "        <ns30:forsteVirkningspunkt>2016-05-01T00:00:00+02:00</ns30:forsteVirkningspunkt>\n" +
                                "        <ns30:beregningsperioderListe>\n" +
                                "            <ns32:beregningsperiode>\n" +
                                "                <ns31:fom>2016-05-01T00:00:00+02:00</ns31:fom>\n" +
                                "                <ns31:uforetidspunkt>2016-02-01T12:00:00+01:00</ns31:uforetidspunkt>\n" +
                                "                <ns31:uforegrad>35</ns31:uforegrad>\n" +
                                "                <ns31:ifu>375000</ns31:ifu>\n" +
                                "                <ns31:inntektBruktIAvkortingen>0</ns31:inntektBruktIAvkortingen>\n" +
                                "                <ns31:ferdiglignetInntekt>false</ns31:ferdiglignetInntekt>\n" +
                                "                <ns31:klagebehandles>false</ns31:klagebehandles>\n" +
                                "            </ns32:beregningsperiode>\n" +
                                "        </ns30:beregningsperioderListe>\n" +
                                "    </ns33:uforeopplysninger>\n" +
                                "</ns33:pensjonsinformasjon>")
                );
    }

    private static String requestBody() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" + lineSeparator() +
                "<xs:schema xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns=\"http://nav.no/pensjon/v1/pensjonsinformasjon\"" + lineSeparator() +
                "           xmlns:uforeopplysninger=\"http://nav.no/pensjon/v1/uforeopplysninger\"" + lineSeparator() +
                "           attributeFormDefault=\"unqualified\" elementFormDefault=\"qualified\" targetNamespace=\"http://nav.no/pensjon/v1/pensjonsinformasjon\">" + lineSeparator() +
                "" + lineSeparator() +
                "    <xs:import namespace=\"http://nav.no/pensjon/v1/uforeopplysninger\" schemaLocation=\"v1.Uforeopplysninger.xsd\"/>" + lineSeparator() +
                "" + lineSeparator() +
                "    <xs:element name=\"pensjonsinformasjon\" type=\"Pensjonsinformasjon\"/>" + lineSeparator() +
                "    <xs:complexType name=\"Pensjonsinformasjon\">" + lineSeparator() +
                "        <xs:all>" + lineSeparator() +
                "            <xs:element name=\"uforeopplysninger\" type=\"uforeopplysninger:v1.Uforeopplysninger\"/>" + lineSeparator() +
                "        </xs:all>" + lineSeparator() +
                "    </xs:complexType>" + lineSeparator() +
                "</xs:schema>";
    }
}
