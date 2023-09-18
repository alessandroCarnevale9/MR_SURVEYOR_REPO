USE MrSurveyorDB;

INSERT INTO category (category_name, image_path, category_description)
VALUES
    ('Misuratori laser Leica DISTO', 'misuratori_laser_leica_disto.png', 'Categoria per i misuratori laser Leica DISTO'),
    ('Livelli laser Leica Lino', 'livelli_laser_leica_lino.png', 'Categoria per i livelli laser Leica Lino'),
    ('Termocamere FLIR', 'termocamere_flir.png', 'Categoria per le termocamere FLIR'),
    ('Termometri IR Flir', 'termometri_ir_flir.png', 'Categoria per i termometri IR Flir'),
    ('Videoscopi', 'videoscopi.png', 'Categoria per i videoscopi'),
    ('Misuratori di umidità', 'misuratori_di_umidità.png', 'Categoria per i misuratori di umidità'),
    ('Termoigrometri Extech', 'termoigrometri_extech.png', 'Categoria per i termoigrometri Extech'),
    ('Altri Misuratori Extech', 'altri_misuratori_extech.png', 'Categoria per altri misuratori Extech'),
    ('Prodotti serie Leica BLK', 'prodotti_serie_leica_blk.png', 'Categoria per i prodotti serie Leica BLK'),
    ('Laser scanner e reality capture', 'laser_scanner_e_reality_capture.png', 'Categoria per i laser scanner e reality capture'),
    ('Droni', 'droni.png', 'Categoria per droni'),
    ('Strumenti Topografici', 'strumenti_topografici.png', 'Categoria per gli strumenti topografici'),
    ('Stazioni totali Leica', 'stazioni_totali_leica.png', 'Categoria per le stazioni totali Leica'),
    ('Sistemi GNSS Leica', 'sistemi_gnss_leica.png', 'Categoria per i sistemi GNSS Leica'),
    ('Livelli laser rotanti Leica', 'livelli_laser_rotanti_leica.png', 'Categoria per i livelli laser rotanti Leica'),
    ('Livelli ottici Leica', 'livelli_ottici_leica.png', 'Categoria per i livelli ottici Leica'),
    ('Treppiedi, aste, prismi e vari', 'treppiedi_aste_prismi_e_vari.png', 'Categoria per treppiedi, aste, prismi e vari');


INSERT INTO subcategory (subcategory_name, subcategory_description, category_name)
VALUES
    -- Sottocategorie per la categoria 'Misuratori laser Leica DISTO'
    ('Accessori per Disto', 'Sottocategoria per accessori per Disto', 'Misuratori laser Leica DISTO'),
    ('Ricambi per Disto', 'Sottocategoria per ricambi per Disto', 'Misuratori laser Leica DISTO'),
    ('Software per Disto', 'Sottocategoria per software per Disto', 'Misuratori laser Leica DISTO'),

    -- Sottocategorie per la categoria 'Livelli laser Leica Lino'
    ('Alimentazione', 'Sottocategoria per alimentazione', 'Livelli laser Leica Lino'),
    ('Ricevitori', 'Sottocategoria per ricevitori', 'Livelli laser Leica Lino'),
    ('Treppiedi, aste e staffe', 'Sottocategoria per treppiedi, aste e staffe', 'Livelli laser Leica Lino'),
    ('Accessori vari per Lino', 'Sottocategoria per accessori vari per Lino', 'Livelli laser Leica Lino'),

    -- Sottocategorie per la categoria 'Termocamere FLIR'
    ('Flir One', 'Sottocategoria per Flir One', 'Termocamere FLIR'),
    ('Serie C', 'Sottocategoria per Serie C', 'Termocamere FLIR'),
    ('Serie EX', 'Sottocategoria per Serie EX', 'Termocamere FLIR'),
    ('Serie EXX', 'Sottocategoria per Serie EXX', 'Termocamere FLIR'),
    ('Serie EXX/DFOV', 'Sottocategoria per Serie EXX/DFOV', 'Termocamere FLIR'),
    ('Serie T', 'Sottocategoria per Serie T', 'Termocamere FLIR'),
    ('Accessori FLIR', 'Sottocategoria per accessori FLIR', 'Termocamere FLIR'),

    -- Sottocategorie per la categoria 'Strumenti Topografici'
    ('Stazioni totali Leica', 'Sottocategoria per le stazioni totali Leica', 'Strumenti Topografici'),
    ('Sistemi GNSS Leica', 'Sottocategoria per i sistemi GNSS Leica', 'Strumenti Topografici'),
    ('Livelli digitali Leica LS', 'Sottocategoria per i livelli digitali Leica LS', 'Strumenti Topografici'),
    ('Basamenti e supporti', 'Sottocategoria per basamenti e supporti', 'Strumenti Topografici'),
    ('Batterie e caricabatterie', 'Sottocategoria per batterie e caricabatterie', 'Strumenti Topografici'),
    ('Paline', 'Sottocategoria per paline', 'Strumenti Topografici'),
    ('Prismi Leica', 'Sottocategoria per prismi Leica', 'Strumenti Topografici'),
    ('Treppiedi', 'Sottocategoria per treppiedi', 'Strumenti Topografici'),

    -- Sottocategorie per la categoria 'Stazioni totali Leica'
    ('Leica FlexLine plus', 'Sottocategoria per Leica FlexLine plus', 'Stazioni totali Leica'),
    ('Leica Captivate', 'Sottocategoria per Leica Captivate', 'Stazioni totali Leica'),

    -- Sottocategorie per la categoria 'Sistemi GNSS Leica'
    ('Controller', 'Sottocategoria per controller', 'Sistemi GNSS Leica'),
    ('Smart antenne', 'Sottocategoria per smart antenne', 'Sistemi GNSS Leica'),

    -- Sottocategorie per la categoria 'Livelli laser rotanti Leica'
   ('Alimentazione', 'Sottocategoria per l\'alimentazione dei livelli laser rotanti Leica', 'Livelli laser rotanti Leica'),
   ('Ricevitori', 'Sottocategoria per i ricevitori dei livelli laser rotanti Leica', 'Livelli laser rotanti Leica'),
   ('Treppiedi e stadie', 'Sottocategoria per treppiedi e stadie dei livelli laser rotanti Leica', 'Livelli laser rotanti Leica'),
   ('Accessori vari', 'Sottocategoria per accessori vari dei livelli laser rotanti Leica', 'Livelli laser rotanti Leica'),

   -- Sottocategorie per la categoria 'Livelli ottici Leica'
    ('Treppiedi e stadie', 'Sottocategoria per treppiedi e stadie dei livelli ottici Leica', 'Livelli ottici Leica'),

    -- Sottocategorie per 'Treppiedi, aste, prismi e vari'
    ('Riduzioni per Aste e Treppiedi', 'Sottocategoria per le riduzioni per aste e treppiedi', 'Treppiedi, aste, prismi e vari'),
    ('Aste', 'Sottocategoria per le aste', 'Treppiedi, aste, prismi e vari'),
    ('Chiodi topografici', 'Sottocategoria per i chiodi topografici', 'Treppiedi, aste, prismi e vari'),
    ('Paline', 'Sottocategoria per le paline', 'Treppiedi, aste, prismi e vari'),
    ('Prismi e supporti', 'Sottocategoria per prismi e supporti', 'Treppiedi, aste, prismi e vari'),
    ('Treppiedi', 'Sottocategoria per i treppiedi', 'Treppiedi, aste, prismi e vari');


INSERT INTO manager (manager_username, manager_password, manager_role)
VALUES
    ('john_doe', 'SecureP@ss1', 'order_manager'),
    ('jane_smith', 'MyP@ssw0rd', 'catalog_manager'),
    ('spike', 'Passion4Profession!', 'catalog_manager'),
    ('leo_boy', 'SWEngPOGGERS@09', 'catalog_manager'),
	('leo_boy', 'SWEngPOGGERS@09', 'order_manager'),
    ('mike_jones', '1234Xy!Z', 'order_manager');


INSERT INTO product (product_image_path, product_name, product_price, product_quantity, product_description, manager_username)
VALUES
    ('leica_disto_s910_p2p_pro_pack.png', 'Leica Disto S910 P2P Pro Pack', 1960.00, 18, 'Misura con precisione e velocità con il kit professionale Leica Disto S910 P2P.', 'jane_smith'),
    ('leica_disto_d810_touch_pack.png', 'Leica Disto D810 touch Pack', 1050.00, 5, 'Tocco e misura con il kit completo Leica Disto D810 touch.', 'spike'),
    ('leica_disto_d110.png', 'Leica Disto D110', 800.00, 12, 'Misura con facilità e precisione grazie al Leica Disto D110.', 'leo_boy'),
    ('adattatore_leica_fta360.png', 'Adattatore Leica FTA360', 250.00, 9, 'Aggiungi versatilità alle tue misurazioni con l\'adattatore Leica FTA360.', 'jane_smith'),
    ('treppiede_leica_tri_100.png', 'Treppiede Leica TRI 100', 200.00, 20, 'Stabile e affidabile, il treppiede Leica TRI 100 è perfetto per le tue esigenze di misurazione.', 'spike'),
    ('treppiede_leica_tri_70.png', 'Treppiede Leica TRI 70', 120.00, 2, 'Portatile e compatto, il treppiede Leica TRI 70 è l\'accessorio ideale per le misurazioni in movimento.', 'leo_boy'),
    ('occhiali_per_laser_glb30.png', 'Occhiali per laser GLB30', 45.00, 7, 'Proteggi i tuoi occhi durante le misurazioni laser con gli occhiali GLB30.', 'jane_smith'),
    ('caricabatteria_leica_uc20.png', 'Caricabatteria Leica UC20', 100.00, 14, 'Mantieni i tuoi strumenti sempre pronti all\'uso con il caricabatteria Leica UC20.', 'spike'),
    ('adattatore_leica_fta360-s_per_disto_s910.png', 'Adattatore Leica FTA360-S per Disto S910', 300.00, 16, 'Massimizza le capacità del tuo Leica Disto S910 con l\'adattatore FTA360-S.', 'leo_boy'),
    ('coperchio_vano_batterie_leica_disto_d1.png', 'Coperchio Vano Batterie Leica DISTO D1', 15.00, 3, 'Ricambio originale Leica per il coperchio del vano batterie del modello DISTO D1.', 'jane_smith'),
    ('software_distodraw_per_android.png', 'Software DistoDraw per Android', 20.00, 19, 'Crea disegni precisi sul tuo dispositivo Android con il software DistoDraw.', 'jane_smith'),
    ('leica_lino_l6r_kit.png', 'Leica Lino L6R Kit', 1200.00, 11, 'Il kit Leica Lino L6R offre una soluzione di livellamento completa.', 'spike'),
    ('leica_lino_l6g_kit.png', 'Leica Lino L6G Kit', 950.00, 8, 'Misura e allinea con precisione utilizzando il kit Leica Lino L6G.', 'leo_boy'),
    ('adattatore_batterie_alkaline_per_leica_lino_serie_new.png', 'Adattatore batterie alkaline per Leica Lino serie New', 60.00, 6, 'Trasforma la tua serie Leica Lino New in un\'opzione alimentata a batterie alkaline.', 'jane_smith'),
    ('batteria_ricaricabile_per_leica_lino_ml180_e_ml90.png', 'Batteria ricaricabile per Leica Lino ML180 e ML90', 95.00, 17, 'Assicurati che il tuo Leica Lino ML180 e ML90 sia sempre pronto all\'uso con questa batteria ricaricabile.', 'spike'),
    ('caricabatterie_per_batterie_per_leica_lino_ml180_e_ml90.png', 'Caricabatterie per batterie per Leica Lino ML180 e ML90', 90.00, 4, 'Ricarica comodamente le batterie del tuo Leica Lino ML180 e ML90 con questo caricabatterie.', 'leo_boy'),
    ('ricevitore_xcr_per_leica_lino_ml180.png', 'Ricevitore XCR per Leica Lino ML180', 650.00, 1, 'Massimizza le prestazioni del tuo Leica Lino ML180 con il ricevitore XCR.', 'jane_smith'),
    ('ricevitore_leica_rgr200.png', 'Ricevitore Leica RGR200', 600.00, 13, 'Ottimizza le tue misurazioni con il ricevitore Leica RGR200.', 'spike'),
    ('treppiede_cet103_in_alluminio_con_elevazione.png', 'Treppiede CET103 in alluminio con elevazione', 289.00, 10, 'Stabile e regolabile, il treppiede CET103 è ideale per le tue esigenze di misurazione.', 'leo_boy'),
    ('stadia_leica_porta_ricevitore_mt._2,5.png', 'Stadia Leica porta ricevitore Mt. 2,5', 70.00, 15, 'Questo stadia Leica è perfetto per il montaggio e la misurazione.', 'jane_smith'),
    ('coperchio_vano_batterie_leica_lino_l2_-_p5_-_p3.png', 'Coperchio vano batterie Leica Lino L2 - P5 - P3', 19.00, 4, 'Coperchio di ricambio per il vano batterie dei modelli Leica Lino L2, P5 e P3.', 'jane_smith'),
    ('termocamera_flir_one_pro_per_android_usb-c.png', 'Termocamera FLIR ONE Pro per Android USB-C', 350.00, 19, 'Visualizza il mondo termico nascosto intorno a te con la termocamera FLIR ONE Pro.', 'spike'),
    ('flir_cx5.png', 'FLIR Cx5', 230.00, 8, 'Imaging termico potente e conveniente con la termocamera FLIR Cx5.', 'leo_boy'),
    ('termocamera_flir_e8_pro.png', 'Termocamera FLIR E8 PRO', 3699.00, 16, 'La termocamera FLIR E8 PRO offre imaging termico avanzato e precisione.', 'jane_smith'),
    ('termocamera_flir_e86_ottica_14-24-42.png', 'Termocamera FLIR E86 ottica 14°/24°/42°', 4250.00, 2, 'Esplora il mondo termico con la termocamera FLIR E86 e le diverse ottiche.', 'spike'),
    ('termocamera_flir_e76+dfov_ottica_14_24.png', 'Termocamera FLIR E76+DFOV (ottica 14°+24°)', 3200.00, 11, 'Imaging termico di alta qualità con l\'ottica versatile della termocamera FLIR E76+DFOV.', 'leo_boy'),
    ('termocamera_flir_t1020_ottica_12-28-45.png', 'Termocamera FLIR T1020 ottica 12°/28°/45°', 2000.00, 14, 'Immagini termiche ad alta risoluzione con la termocamera FLIR T1020 e l\'ottica intercambiabile.', 'jane_smith'),
    ('alimentatore_per_termocamera_flir_ex.png', 'Alimentatore per Termocamera FLIR Ex', 129.00, 7, 'Assicurati che la tua termocamera FLIR Ex sia sempre pronta all\'uso con questo alimentatore.', 'spike'),
    ('custodia_rigida_per_termocamera_flir_serie_ex.png', 'Custodia rigida per termocamera FLIR serie Ex', 229.00, 12, 'Proteggi la tua termocamera FLIR Ex con questa custodia rigida.', 'leo_boy'),
    ('termometro_flir_tg267.png', 'Termometro FLIR TG267', 499.00, 5, 'Misurazioni affidabili della temperatura con il termometro FLIR TG267.', 'jane_smith'),
    ('termometro_flir_tg165-x.png', 'Termometro FLIR TG165-X', 419.00, 10, 'Misura con precisione le temperature con il termometro FLIR TG165-X.', 'jane_smith'),
    ('termometro_flir_tg297.png', 'Termometro FLIR TG297', 1200.00, 17, 'Misurazioni avanzate delle temperature con il termometro FLIR TG297.', 'spike'),
    ('br250_-_endoscopio_elettronico.png', 'BR250 - Endoscopio elettronico', 282.00, 3, 'Esplora ambienti difficili da raggiungere con l\'endoscopio elettronico BR250.', 'leo_boy'),
    ('br80_-_endoscopio_elettronico.png', 'BR80 - Endoscopio elettronico', 124.00, 1, 'Ispeziona e ispeziona con facilità utilizzando l\'endoscopio elettronico BR80.', 'jane_smith'),
    ('flir_vs290.png', 'FLIR VS290', 1400.00, 6, 'Soluzioni di ispezione visiva avanzate con il FLIR VS290.', 'spike'),
    ('mo57_igrometro_senza_puntale.png', 'MO57 Igrometro senza puntale', 69.00, 20, 'Misura l\'umidità con precisione senza l\'uso di un puntale con il MO57.', 'leo_boy'),
    ('flir_mr59_igrometro_con_sonda_a_sfera_e_bluetooth.png', 'FLIR MR59 Igrometro con sonda a sfera e Bluetooth', 249.00, 15, 'Monitora l\'umidità in modo efficiente con il FLIR MR59 con sonda a sfera e Bluetooth.', 'jane_smith'),
    ('irc130_termometro_ir_per_termocamera_con_msx.png', 'IRC130 Termometro IR per termocamera con MSX', 505.00, 9, 'Ottieni misurazioni termiche precise con il termometro IR IRC130 con supporto MSX.', 'spike'),
    ('sd700_-_misuratore_ambientale.png', 'SD700 - Misuratore ambientale', 272.00, 18, 'Misura una varietà di parametri ambientali con il misuratore SD700.', 'leo_boy'),
    ('Extech-EMF510.png', 'EMF510: Misuratore EMF/ELF', 118.00, 13, 'Rileva campi elettrici e magnetici con il misuratore EMF510 Extech.', 'jane_smith'),
    ('Extech_CO15.png', 'CO15: Misuratore di monossido di carbonio (CO)', 124.00, 16, 'Misura il monossido di carbonio (CO) nell\'aria con precisione utilizzando il misuratore CO15.', 'jane_smith'),
    ('nuovo_leica_blk360.png', 'Nuovo Leica BLK360', 30000.00, 2, 'Esperienza di scansione laser 3D avanzata con il nuovo Leica BLK360.', 'spike'),
    ('leica_blk2go_-_imaging_laser_scanner_portatile.png', 'Leica BLK2GO - Imaging laser scanner portatile', 25000.00, 11, 'Scansione laser portatile e imaging con il Leica BLK2GO.', 'leo_boy'),
    ('leica_blk3d.png', 'Leica BLK3D', 26550.00, 7, 'Misura facilmente le distanze e crea modelli 3D con il Leica BLK3D.', 'jane_smith'),
    ('leica_p50.png', 'Leica P50', 45000.00, 9, 'Il Leica P50 offre misurazioni precise per applicazioni di rilevamento.', 'spike'),
    ('leica_p30_e_p40.png', 'Leica P30 e P40', 46500.00, 12, 'Scansiona e cattura dati con precisione con i modelli Leica P30 e P40.', 'leo_boy'),
    ('leica_rtc360_3d.png', 'Leica RTC360 3D', 50000.00, 10, 'Scansione laser 3D efficiente e completa con il Leica RTC360.', 'jane_smith'),
    ('drone_matrice_200_serie_v2.png', 'Drone Matrice 200 Serie V2', 4999.00, 4, 'Esplora e mappa aree con il drone Matrice 200 Serie V2.', 'spike'),
    ('drone_parrot_anafi_300gr.png', 'Drone Parrot Anafi 300gr', 3500.00, 6, 'Il drone Parrot Anafi offre flessibilità e prestazioni leggere.', 'leo_boy'),
    ('drone_mavic_mini_249gr.png', 'Drone Mavic Mini 249gr', 5000.00, 14, 'Esplora e cattura con facilità con il drone Mavic Mini.', 'jane_smith'),
    ('stazione_totale_leica_ts13.png', 'Stazione Totale Leica TS13', 25500.00, 19, 'Misurazioni precise e rilevamenti topografici con la stazione totale Leica TS13.', 'jane_smith'),
    ('stazione_totale_leica_viva_ts16.png', 'Stazione Totale Leica Viva TS16', 30000.00, 18, 'Lavori di rilevamento e costruzione efficienti con la stazione totale Leica Viva TS16.', 'spike'),
    ('stazione_totale_leica_nova_ts60.png', 'Stazione Totale Leica Nova TS60', 40500.00, 1, 'Massima precisione e funzionalità avanzate con la stazione totale Leica Nova TS60.', 'leo_boy'),
    ('stazione_totale_leica_ts10.png', 'Stazione Totale Leica TS10', 20650.00, 5, 'Misurazioni topografiche e rilevamenti di alta qualità con la stazione totale Leica TS10.', 'jane_smith'),
    ('stazione_totale_leica_ts03.png', 'Stazione Totale Leica TS03', 10900.00, 3, 'Efficienza nei rilevamenti topografici con la stazione totale Leica TS03.', 'spike'),
    ('stazione_totale_leica_ts07.png', 'Stazione Totale Leica TS07', 15000.00, 8, 'Rilevamenti precisi e affidabili con la stazione totale Leica TS07.', 'leo_boy'),
    ('leica_gs07_-_smart_antenna_gnss.png', 'Leica GS07 - Smart antenna GNSS', 3000.00, 20, 'Posizionamento GNSS preciso e affidabile con l\'antenna smart Leica GS07.', 'jane_smith'),
    ('leica_gs18_t_-_il_rover_rtk_gnss_più_veloce_al_mondo.png', 'Leica GS18 T - Il rover RTK GNSS più veloce al mondo', 6500.00, 5, 'Massima velocità e precisione nel posizionamento GNSS con il rover Leica GS18 T.', 'spike'),
    ('leica_controller_cs20.png', 'Leica Controller CS20', 2500.00, 13, 'Controllo intuitivo e facile con il controller Leica CS20.', 'leo_boy'),
    ('leica_viva_gs16_-_smart_antenna_gnss_ad_apprendimento_autonomo.png', 'Leica Viva GS16 - Smart antenna GNSS ad apprendimento autonomo', 5500.00, 17, 'Antenna smart GNSS con capacità di apprendimento autonomo, ideale per applicazioni geospaziali.', 'jane_smith'),
    ('leica_rugby_chameleon_clh.png', 'Leica Rugby Chameleon CLH', 2000.00, 15, 'Misurazioni precise con il Leica Rugby Chameleon CLH.', 'jane_smith'),
    ('livello_laser_leica_rugby_serie_680.png', 'Livello Laser Leica Rugby Serie 680', 750.00, 12, 'Livellamento preciso con il Livello Laser Leica Rugby Serie 680.', 'spike'),
    ('batteria_ricaricabile_rugby_serie_100_e_200.png', 'Batteria ricaricabile Rugby serie 100 e 200', 168.00, 6, 'Alimenta i tuoi strumenti con la batteria ricaricabile per la serie 100 e 200 di Leica Rugby.', 'leo_boy'),
    ('Caricatteria-Rugby-300_400.png', 'Caricatteria per Leica Rugby 300/400', 107.00, 9, 'Carica comodamente il tuo Leica Rugby 300/400 con questa caricabatteria.', 'jane_smith'),
    ('telecomando_rc400_per_leica_rugby.png', 'Telecomando RC400 per Leica Rugby', 177.00, 14, 'Controllo remoto preciso con il telecomando RC400 per strumenti Leica Rugby.', 'spike'),
    ('ricevitore_rod-eye_160_digital_per_leica_rugby.png', 'Ricevitore Rod-Eye 160 Digital per Leica Rugby', 338.00, 4, 'Rilevamento accurato delle misurazioni con il ricevitore Rod-Eye 160 Digital per Leica Rugby.', 'leo_boy'),
    ('treppiede_in_alluminio_con_elevazione_cm_74-122.png', 'Treppiede in alluminio con elevazione Cm. 74/122', 210.00, 10, 'Stabile supporto per strumenti con il treppiede in alluminio con elevazione.', 'jane_smith'),
    ('adattatore_multiuso_per_livelli_laser_leica.png', 'Adattatore multiuso per livelli laser Leica', 263.00, 1, 'Versatile adattatore per livelli laser Leica.', 'spike'),
    ('livelli_ottici_leica_serie_na300.png', 'Livelli ottici Leica serie NA300', 400.00, 17, 'Livelli ottici di alta qualità della serie NA300 di Leica.', 'leo_boy'),
    ('livelli_ottici_leica_serie_na500.png', 'Livelli ottici Leica serie NA500', 350.00, 15, 'Livelli ottici affidabili della serie NA500 di Leica.', 'jane_smith'),
    ('livelli_ottici_leica_serie_na700.png', 'Livelli ottici Leica serie NA700', 330.00, 5, 'Livelli ottici di alta precisione della serie NA700 di Leica.', 'jane_smith'),
    ('gst20_treppiede_in_legno.png', 'GST20, Treppiede in legno', 400.00, 13, 'Stabile supporto in legno con il treppiede GST20.', 'spike'),
    ('gst05_treppiede_in_legno.png', 'GST05, Treppiede in legno', 245.00, 19, 'Supporto in legno affidabile con il treppiede GST05.', 'leo_boy'),
    ('Riduzione-da-piolo-a-1-4-194x194.png', 'Riduzione per asta da piolo Leica a 1/4"', 19.00, 11, 'Riduzione per adattare un asta da piolo Leica a 1/4".', 'jane_smith'),
    ('Asta-Telescopica-5-metri-con-attacco-Leica.png', 'Asta telescopica 5 metri con attacco Leica/baionetta', 149.00, 3, 'Asta telescopica da 5 metri con attacco Leica/baionetta per il posizionamento.', 'spike'),
    ('Caposaldo_per_topografia-1-194x194.png', 'Caposaldo in ferro ø40', 11.00, 18, 'Caposaldo in ferro con diametro di 40 mm.', 'leo_boy'),
    ('conf._30_pezzi_palina_ottagonale_in_legno_con_punta_a_chiodo.png', 'Conf. 30 pezzi palina ottagonale in legno con punta a chiodo', 225.00, 8, 'Confezione da 30 pezzi di paline ottagonali in legno con punta a chiodo.', 'jane_smith'),
    ('conf._30_pezzi_palina_in_ferro_plastificata_in_1_pz.png', 'Conf. 30 pezzi palina in ferro plastificata in 1 pz', 190.00, 16, 'Confezione da 30 pezzi di paline in ferro plastificato, 1 pezzo per palina.', 'spike'),
    ('gpr121_prisma_completo_pro_leica.png', 'GPR121 Prisma completo PRO Leica', 370.00, 7, 'Prisma completo PRO Leica con alta precisione.', 'leo_boy'),
    ('prisma_completo_con_attacco_leica.png', 'Prisma completo con attacco Leica', 215.00, 20, 'Prisma completo con attacco Leica per misurazioni accurate.', 'jane_smith'),
    ('treppiede_in_alluminio_102-167_a_testa_piana.png', 'Treppiede in alluminio 102/167 a Testa Piana', 400.00, 1, 'Treppiede in alluminio con altezza regolabile da 102 cm a 167 cm e testa piana.', 'spike'),
    ('base_a_stella_per_treppiede.png', 'Base a stella per treppiede', 45.00, 2, 'Base a stella per un supporto stabile del treppiede.', 'leo_boy');

INSERT INTO has_category_product (product_id, category_name)
VALUES
    (1, 'Misuratori laser Leica DISTO'),
    (2, 'Misuratori laser Leica DISTO'),
    (3, 'Misuratori laser Leica DISTO'),
    (4, 'Misuratori laser Leica DISTO'),
    (5, 'Misuratori laser Leica DISTO'),
    (5, 'Livelli laser Leica Lino'),
    (6, 'Misuratori laser Leica DISTO'),
    (6, 'Livelli laser Leica Lino'),
    (7, 'Misuratori laser Leica DISTO'),
    (7, 'Livelli laser Leica Lino'),
    (8, 'Misuratori laser Leica DISTO'),
    (9, 'Misuratori laser Leica DISTO'),
    (10, 'Misuratori laser Leica DISTO'),
    (11, 'Misuratori laser Leica DISTO'),
    (12, 'Livelli laser Leica Lino'),
    (13, 'Livelli laser Leica Lino'),
    (14, 'Livelli laser Leica Lino'),
    (15, 'Livelli laser Leica Lino'),
    (16, 'Livelli laser Leica Lino'),
    (17, 'Livelli laser Leica Lino'),
    (18, 'Livelli laser Leica Lino'),
    (19, 'Livelli laser Leica Lino'),
    (20, 'Livelli laser Leica Lino'),
    (21, 'Livelli laser Leica Lino'),
    (22, 'Termocamere FLIR'),
    (23, 'Termocamere FLIR'),
    (24, 'Termocamere FLIR'),
    (25, 'Termocamere FLIR'),
    (26, 'Termocamere FLIR'),
    (27, 'Termocamere FLIR'),
    (28, 'Termocamere FLIR'),
    (29, 'Termocamere FLIR'),
    (30, 'Termometri IR Flir'),
    (31, 'Termometri IR Flir'),
    (32, 'Termometri IR Flir'),
    (33, 'Videoscopi'),
    (34, 'Videoscopi'),
    (35, 'Videoscopi'),
    (36, 'Misuratori di umidità'),
    (37, 'Misuratori di umidità'),
    (38, 'Termoigrometri Extech'),
    (39, 'Altri Misuratori Extech'),
    (40, 'Altri Misuratori Extech'),
    (41, 'Altri Misuratori Extech'),
    (42, 'Prodotti serie Leica BLK'),
    (43, 'Prodotti serie Leica BLK'),
    (44, 'Prodotti serie Leica BLK'),
    (45, 'Laser scanner e reality capture'),
    (46, 'Laser scanner e reality capture'),
    (47, 'Laser scanner e reality capture'),
    (48, 'Droni'),
    (49, 'Droni'),
    (50, 'Droni'),
    (51, 'Strumenti Topografici'),
    (51, 'Stazioni totali Leica'),
    (52, 'Strumenti Topografici'),
    (52, 'Stazioni totali Leica'),
    (53, 'Strumenti Topografici'),
    (53, 'Stazioni totali Leica'),
    (54, 'Strumenti Topografici'),
    (54, 'Stazioni totali Leica'),
    (55, 'Strumenti Topografici'),
    (55, 'Stazioni totali Leica'),
    (56, 'Strumenti Topografici'),
    (56, 'Stazioni totali Leica'),
    (57, 'Strumenti Topografici'),
    (57, 'Sistemi GNSS Leica'),
    (58, 'Strumenti Topografici'),
    (58, 'Sistemi GNSS Leica'),
    (59, 'Strumenti Topografici'),
    (59, 'Stazioni totali Leica'),
    (59, 'Sistemi GNSS Leica'),
    (60, 'Strumenti Topografici'),
    (60, 'Sistemi GNSS Leica'),
    (61, 'Livelli laser rotanti Leica'),
    (62, 'Livelli laser rotanti Leica'),
    (63, 'Livelli laser rotanti Leica'),
    (64, 'Livelli laser rotanti Leica'),
    (65, 'Livelli laser rotanti Leica'),
    (66, 'Livelli laser rotanti Leica'),
    (67, 'Livelli laser rotanti Leica'),
    (68, 'Livelli laser rotanti Leica'),
    (69, 'Livelli ottici Leica'),
    (70, 'Livelli ottici Leica'),
    (71, 'Livelli ottici Leica'),
    (72, 'Livelli ottici Leica'),
    (72, 'Strumenti Topografici'),
    (72, 'Treppiedi, aste, prismi e vari'),
    (73, 'Livelli ottici Leica'),
    (73, 'Strumenti Topografici'),
    (73, 'Treppiedi, aste, prismi e vari'),
    (74, 'Treppiedi, aste, prismi e vari'),
    (75, 'Treppiedi, aste, prismi e vari'),
    (76, 'Treppiedi, aste, prismi e vari'),
    (77, 'Treppiedi, aste, prismi e vari'),
    (78, 'Treppiedi, aste, prismi e vari'),
    (79, 'Treppiedi, aste, prismi e vari'),
    (79, 'Strumenti Topografici'),
    (80, 'Treppiedi, aste, prismi e vari'),
    (81, 'Livelli ottici Leica'),
    (81, 'Strumenti Topografici'),
    (81, 'Treppiedi, aste, prismi e vari'),
    (82, 'Livelli ottici Leica'),
    (82, 'Strumenti Topografici'),
    (82, 'Treppiedi, aste, prismi e vari');

INSERT INTO has_subcategory_product (product_id, subcategory_id)
VALUES
    (4, 1),
    (5, 1),
    (5, 6),
    (6, 1),
    (6, 6),
    (7, 1),
    (7, 7),
    (8, 1),
    (9, 1),
    (10, 2),
    (11, 3),
    (15, 4),
    (16, 4),
    (17, 5),
    (18, 5),
    (19, 6),
    (20, 6),
    (21, 7),
    (22, 8),
    (23, 9),
    (24, 10),
    (25, 11),
    (26, 12),
    (27, 13),
    (28, 14),
    (29, 14),
    (51, 15),
    (51, 24),
    (52, 15),
    (52, 24),
    (53, 15),
    (53, 24),
    (54, 15),
    (54, 23),
    (55, 15),
    (55, 23),
    (56, 15),
    (56, 23),
    (57, 16),
    (57, 26),
    (58, 16),
    (58, 26),
    (59, 15),
    (59, 16),
    (59, 24),
    (59, 25),
    (60, 16),
    (60, 26),
    (63, 27),
    (64, 27),
    (65, 28),
    (66, 28),
    (67, 29),
    (68, 30),
    (72, 31),
    (72, 22),
    (72, 37),
    (73, 31),
    (73, 22),
    (73, 37),
    (74, 32),
    (75, 33),
    (76, 34),
    (77, 35),
    (78, 35),
    (79, 36),
    (79, 21),
    (80, 36),
    (81, 31),
    (81, 22),
    (81, 37),
    (82, 31),
    (82, 22),
    (82, 37);


INSERT INTO end_user (end_user_name, end_user_surname, end_user_email, end_user_password, end_user_region, end_user_province, end_user_city, end_user_street, end_user_cap, end_user_house_number)
VALUES
    ('Alice', 'Johnson', 'alice@example.com', 'Pa$$w0rd', 'California', 'Los Angeles', 'Los Angeles', 'Main Street', '90001', 123),
    ('Bob', 'Smith', 'bob@example.com', 'P@ssw0rd!', 'New York', 'New York City', 'New York City', 'Broadway', '10001', 456),
    ('Charlie', 'Brown', 'charlie@example.com', '5ecr3T&K', 'Texas', 'Austin', 'Austin', 'Lake View Drive', '78701', 789),
    ('David', 'Williams', 'david@example.com', 'H3llo@W0rld', 'Florida', 'Miami', 'Miami', 'Ocean Avenue', '33101', 456),
    ('Emily', 'Davis', 'emily@example.com', 'Qwerty@123', 'Washington', 'Seattle', 'Seattle', 'Pine Street', '98101', 789),
    ('Frank', 'Miller', 'frank@example.com', 'Pass!w0rd', 'Illinois', 'Chicago', 'Chicago', 'State Street', '60601', 101),
    ('Grace', 'Wilson', 'grace@example.com', '7eleven#', 'Massachusetts', 'Boston', 'Boston', 'Commonwealth Avenue', '02101', 202),
    ('Henry', 'Anderson', 'henry@example.com', 'P@ss123!', 'Pennsylvania', 'Philadelphia', 'Philadelphia', 'Market Street', '19101', 303),
    ('Isabella', 'Martinez', 'isabella@example.com', 'M0n3y$hot', 'Texas', 'Houston', 'Houston', 'Westheimer Road', '77001', 404),
    ('Jack', 'Harris', 'jack@example.com', 'L3tmein@1', 'California', 'San Francisco', 'San Francisco', 'Mission Street', '94101', 505);
    