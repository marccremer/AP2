import java.util.Random

class Laufstrecke(var Strecke_laenge : Int,var Strecke_name :String,var Strecke_sehenswurd : List<String>) {


	fun testing(): String{
		return "ass"
	}

	override fun toString(): String{
		val tmp = Strecke_sehenswurd.toMutableList()
		tmp.add(tmp.lastIndex," und ")
		return "Die Strecke ${Strecke_name}" +
		 "(${Strecke_laenge} m) verläuft entlang " + 
		 "${tmp.joinToString(" ")}"
	}

	fun ist_gleich(streckeB : Laufstrecke) : Boolean{
		if (Strecke_name == streckeB.Strecke_name){
			return true
		}else{
			return false
		}
	}
} 


fun main(){
	val beispielNamen = arrayOf("Nürburgring","Hockenheimring","Lausitzring","Rallye Monte Carlo","Le Mans")
	val beispielSehenswuerdigkeit = arrayOf("dem Kolosseum in Rom","der Louvre","die Sixtinische Kapelle","die Freiheitsstatue","derEiffelturm","die Sagrada Família")
	val strecken = Array(5) { i -> Laufstrecke(2,beispielNamen[i],beispielSehenswuerdigkeit.slice(0..Random().nextInt(5)))}
	//val random = Random()
	ausgabeAlle(strecken)
	for (x in 0..4){
		strecken[x].Strecke_laenge = x+1000 + Random().nextInt(300)
	}
	val min = 1150
	val max = 1200 
	println("ausgabeMinMax zwischen $min und $max")
	ausgabeMinMax(strecken,min,max)
	val ziel = 4000
	println("ausgabeBisZielMeterErreicht für $ziel Meter")
	ausgabeBisZielMeterErreicht(strecken,ziel)
	println("ausgabeBisStrecke für ${strecken[2].Strecke_name}.")
	ausgabeBisStrecke(strecken[2],strecken)
	ausgabeKuerzesteStrecke(strecken)
	ausgabeLaengsteStrecke(strecken)
}

fun ausgabeAlle(strecken : Array<Laufstrecke>) {
	for (strecke in strecken){
		println(strecke.toString())
	}
}

fun ausgabeMinMax(strecken : Array<Laufstrecke>,min : Int,max: Int){
	for (strecke in strecken){
		if ((strecke.Strecke_laenge > min) and ( strecke.Strecke_laenge < max)){
			println(strecke.toString())
		}
	}
}

fun ausgabeBisZielMeterErreicht ( strecken : Array<Laufstrecke> ,zielMeter: Int) {
	var gelaufen = 0
	println("Wir wollen $zielMeter Meter laufen.\nLos,Gehts")
	for (strecke in strecken){
		if(gelaufen < zielMeter){
			gelaufen += strecke.Strecke_laenge
			println("""|Wir haben sind Strecke ${strecke.Strecke_name} gelaufen.
				       |Das heist wir sind ${gelaufen} Meter glaufen""".trimMargin())
		}
	println("""|Wir sind am unserem Ziel von $zielMeter Meter angekommen.
		       |Insgesamt sind wir ${gelaufen} Meter glaufen""".trimMargin())
	}
// Durchlaufe alle Strecken
// Addiere, wie viele Meter schon gelaufen sind
// Wenn zielMeter erreicht ist: Dann höre auf zu laufen
// Nachdem alle Strecken gelaufen sind:
// Gib die Anzahl der gelaufenen Meter und der zielMeter aus
}
fun ausgabeBisStrecke (letzteStrecke : Laufstrecke, strecken: Array<Laufstrecke>) {
	var nStrecke = 0 
	println("Wir wollen bis zum ${letzteStrecke.Strecke_name} laufen")
	outer@ for (strecke in strecken){
		if (strecken[nStrecke].ist_gleich(letzteStrecke)){
			println("Wir haben den von ${strecken[nStrecke].Strecke_name} erreicht.")
			break@outer
		}else{
			println("Wir laufen auf ${strecken[nStrecke].Strecke_name}.")
		}
		nStrecke += 1
	}
	
// Laufe alle Strecken des Arrays bis zur letzten Strecke, danach höre auf
// Gib jede gelaufene Strecke auf der Konsole aus
}
fun ausgabeKuerzesteStrecke ( strecken : Array<Laufstrecke> ) {
	var candidate = strecken[0]
	for (streckeB in strecken){
		if(candidate.Strecke_laenge > streckeB.Strecke_laenge){
			candidate = streckeB
		}
	}
	println("""|${candidate.Strecke_name} ist die kürzeste Strecke mit 
		       |${candidate.Strecke_laenge} m Länge """.trimMargin())
// Gib nur die Strecke mit der kürzesten Länge auf der Konsole aus
}
fun ausgabeLaengsteStrecke ( strecken : Array<Laufstrecke> ) {
	var candidate = strecken[0]
	for (streckeB in strecken){
		if(candidate.Strecke_laenge < streckeB.Strecke_laenge){
			candidate = streckeB
		}
	}
	println("""|${candidate.Strecke_name} ist die längste Strecke mit 
		       |${candidate.Strecke_laenge} m Länge """.trimMargin())
// Gib nur die Strecke mit der maximalen Länge auf der Konsole aus
}

fun berechneGesamteLauflaenge ( strecken : Array<Laufstrecke>) : Int {
	var acc = 0
	for (strecke in strecken){
		acc += strecke.Strecke_laenge
	}
return acc
}
// Berechne, wie viele Strecken gelaufen werden müssen, um
// die mit zielMeter festgelegten Meter zu laufen
fun berechneAnzahlStrecken ( strecke : Laufstrecke ,zielMeter : Int ) : Int {
	var acc = 0
	var sacc = 0
	while(acc < zielMeter){
		acc += strecke.Strecke_laenge
		sacc += 1
		}
	return sacc
}

// Prüfe, ob bei einer der strecken im Array die gesuchte
// Sehenswürdigkeit dabei ist.
fun sehensWuerdigkeitDabei ( sehenswuerdigkeit : String,
strecken: Array<Laufstrecke>) : Boolean {
	for ( s in strecken ){
		if ( sehenswuerdigkeit in s.Strecke_sehenswurd){
			return true
		}
	}
	return false	
}

// Gib die durchschnittliche Länge der Strecken zurück
fun durchschnittsLaenge (strecken : Array<Laufstrecke>) : Int {
	var avg = 0
	var nStrecke = 0

	for (s in strecken){
		avg += s.Strecke_laenge
		nStrecke += 1
	}
	return (avg/nStrecke)
}

// Gib die Strecke mit der höchsten Länge zurück:
fun laengsteStrecke (strecken : Array<Laufstrecke>) : Laufstrecke {
	var candidate = strecken[0]
	for (streckeB in strecken){
		if(candidate.Strecke_laenge > streckeB.Strecke_laenge){
			candidate = streckeB
		}
	}
	return candidate
}
// Gib die Strecke mit der kürzesten Länge zurück:
fun kuerzesteStrecke (strecken : Array<Laufstrecke>) : Laufstrecke  {
	var candidate = strecken[0]
	for (streckeB in strecken){
		if(candidate.Strecke_laenge < streckeB.Strecke_laenge){
			candidate = streckeB
		}
	}
	return candidate
}
// Gib eine Strecke zurück, die an der sehenswuerdigkeit vorbei führt
fun streckeMitSehenswuerdigkeit
( sehenswuerdigkeit : String , strecken : Array<Laufstrecke>) : Laufstrecke {
	for ( s in strecken ){
		if ( sehenswuerdigkeit in s.Strecke_sehenswurd){
			return s
		}
	}
	return Laufstrecke(0,"",listOf(" "," "))	
}
// Gib von strecke1 und strecke 2 die längere Strecke zurück
fun laengereStrecke (strecke1 : Laufstrecke ,
strecke2 : Laufstrecke) : Laufstrecke =  if (strecke1.Strecke_laenge > strecke2.Strecke_laenge) strecke1 else strecke2