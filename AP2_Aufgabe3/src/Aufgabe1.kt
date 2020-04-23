import java.util.Random

class Laufstrecke(var Strecke_laenge : Int,var Strecke_name :String,var Strecke_sehenswurd : String) {


	fun testing(): String{
		return "ass"
	}

	override fun toString(): String{
		return "Die Strecke ${Strecke_name}" +
		 "(${Strecke_laenge} m) verläuft entlang " + 
		 "${Strecke_sehenswurd}"
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
	val beispielnamen = arrayOf("Nürburgring","Hockenheimring","Lausitzring","Rallye Monte Carlo","Le Mans")
	val strecken = Array(5) { i -> Laufstrecke(2,beispielnamen[i],"Bergstadt,Talstadt.")}
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