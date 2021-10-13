# Tesi Ostuni Mario
Per poter utilizzare il web server bisogna installare dal marketplace di eclipse lo "Spring Tools" e scaricare le librerie HTMLUnit e Jsoup
Una volta aver effettuato il run bisogna utilizzare ngrok. Ngrok è un reverse proxy server cross-platform con cui è possibile esporre un server locale, collocato dietro NAT, e firewall alla rete Internet tramite secure tunnel.
Gli endpoint da utilizzare sono: dibi/submit e dibi/values (esclusivamente per il form dedicato ad i professori per permettere la compilazione automatica). 
Inserire questi due endpoint subito dopo la fine dell'url generato con ngrok.
# Esempio: 
ngrok genera l'url "http://6e50-193-204-189-14.ngrok.io", per collegare i conversational form al web service bisognerà scrivere "http://6e50-193-204-189-14.ngrok.io/dibi/submit"
