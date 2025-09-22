// Lets create an adventure on Mars
// Import a library that will let us get user input
import java.util.Scanner;

// Create the main class for our program
public class Main {

    // starting point for all java programs
    public static void main(String[] args) {
        // Instance-style locals inside main (no separate play method)
        int ShipHealth = 5;
        int CrewTrust = 5;
        int TimeRemaining = 3;

        // Story flags as integers (0 = false, 1 = true)
        int RecoveredFragment = 0;   // obtained in Scenario 2 by boarding
        int DecodedHandshake = 0;    // set in Scenario 3 by decoding/completing
        int EntityBenevolent = 0;    // inferred in Scenario 4 based on engagement

        // create the scanner to use when we need user input
        Scanner input = new Scanner(System.in);

        // INTRO 
        System.out.println("\n========================================\n" + 
                        "   STAR TREK: THE WHISPER BEYOND NEPTUNE\n" + 
                        "========================================\n" + 
                        "\n" + 
                        "\"A new anomaly, a timeless choice. \n" + 
                        "Will you protect your crew, or risk it all \n" + 
                        "for the pursuit of discovery?\"\n");

        System.out.println("Stardate 47521.4\n");
        System.out.println("You are the Captain of the USS Horizon, a Nova-class science vessel near Neptune.");
        System.out.println("A repeating subspace transmission has no match in Starfleet records.\n");
        System.out.println("As Captain, balance discovery, safety, and trust. The anomaly awaits your command.\n");

        System.out.println("This interactive story is inspired by");
        System.out.println("the vision of Gene Roddenberry and the many talented writers of Star Trek.");
        System.out.println("This fan-made project pays homage to their legacy in the spirit of creative exploration.\n");

        System.out.println("Before we begin...");
        System.out.println("Starfleet requires proper records.");
        System.out.print("Please enter your Commander’s name: ");
        String commanderName = input.nextLine();  // store player’s chosen name
        System.out.println("\nWelcome aboard, Commander " + commanderName + ".");
        System.out.println("The crew of the USS Horizon awaits your orders...");

        // Explain rules in-story
        System.out.println("\nThe hum of the USS Horizon’s engines fills the ready room. Outside the viewport, Neptune glows in shades of icy blue.");
        System.out.println("Lieutenant Commander Data: \"Every decision will carry consequences, Captain—ship integrity, crew trust, and time.\"");
        System.out.println("Counselor Troi: \"If any reach zero, the mission ends—by destruction, mutiny, or collapse of the anomaly.\"");
        System.out.println("You rise. \"Then it’s decided. We proceed carefully. The Whisper awaits.\"\n");

        // Transition to bridge
        System.out.println("You step onto the bridge. Consoles hum. Sensors chirp in cautious rhythms.");
        System.out.println("Worf: \"Captain, the signal is strengthening. Periodicity: seven-point-three seconds. Source: beyond Neptune.\"");
        System.out.println("Data: \"Pattern is non-random. It may be an attempt at communication, though it matches no protocol.\"");
        System.out.println("Troi: \"I sense tension... and a hidden intent. It’s faint—like a whisper in the dark.\"\n");

        // SCENARIO 1: THE SIGNAL 
        System.out.println("A mysterious subspace transmission pulses just beyond Neptune. Unknown in origin, its pattern hints at intelligence.");
        System.out.println("What are your orders, Captain?");
        System.out.println("Enter 1 to Scan the signal with long-range sensors.");
        System.out.println("Enter 2 to Ignore it and continue patrol.");
        System.out.print("> ");
        int choice1 = input.nextInt();

        if (choice1 == 1) {
            System.out.println("\nYou order Data to amplify the sensors. Power reroutes; the bridge lights dim.");
            System.out.println("The transmission resolves into a complex mathematical waveform.");
            System.out.println("Effect: -1 Time, +1 Crew Trust");
            TimeRemaining = TimeRemaining - 1;
            CrewTrust = CrewTrust + 1;

            System.out.println("\n[Transition] A scarred vessel drifts just beyond Neptune—source of the transmission.");
        } else if (choice1 == 2) {
            System.out.println("\nYou wave off the anomaly. \"We have our patrol orders. Continue course.\"");
            System.out.println("Effect: +1 Ship Health, -1 Crew Trust");
            ShipHealth = ShipHealth + 1;
            CrewTrust = CrewTrust - 1;

            System.out.println("\n[Transition] An hour later, a derelict appears on the earlier vector. Fate circles back.");
        } else {
            System.out.println("\nIndecision costs momentum. The signal continues unanswered.");
            System.out.println("Effect: -1 Time, -1 Crew Trust");
            TimeRemaining = TimeRemaining - 1;
            CrewTrust = CrewTrust - 1;

            System.out.println("\n[Transition] The transmission cuts off; sensors find a drifting vessel at the last known source.");
        }

        // Status and game over gate
        System.out.println("\nMission status — Ship: " + ShipHealth + " | Crew: " + CrewTrust + " | Time: " + TimeRemaining + "\n");
        if (checkGameOver(ShipHealth, CrewTrust, TimeRemaining) != 0) { input.close(); return; }

        //  SCENARIO 2: THE DERELICT 
        System.out.println("\n=== SCENARIO 2: THE DERELICT ===");
        System.out.println("The vessel tumbles, hull an alloy of Federation design fused with… something alien.");
        System.out.println("Worf: \"No power signatures. Minimal rotation.\"");
        System.out.println("Data: \"Recommend caution, Captain.\"");
        System.out.println("\nYour options:");
        System.out.println("1) Board with an away team (Worf, Data, Geordi).");
        System.out.println("2) Conduct a deep scan from a safe distance.");
        System.out.println("3) Tractor into a safer orbit and stand by.");
        System.out.print("> ");
        int choice2 = input.nextInt();

        if (choice2 == 1) {
            System.out.println("\nThe away team transports. Inside: biomaterial lattices fused with Starfleet bulkheads.");
            System.out.println("A venting jolt rocks Horizon.");
            System.out.println("Effect: -1 Ship Health, +2 Crew Trust, -1 Time");
            ShipHealth = ShipHealth - 1;
            CrewTrust = CrewTrust + 2;
            TimeRemaining = TimeRemaining - 1;

            System.out.println("Data recovers a core fragment: the signal is a mathematical handshake—unfinished.");
            RecoveredFragment = 1;
        } else if (choice2 == 2) {
            System.out.println("\n\"Hold position. Full-spectrum scans.\" Algorithms peel back interference.");
            System.out.println("Effect: -2 Time, +1 Crew Trust");
            TimeRemaining = TimeRemaining - 2;
            CrewTrust = CrewTrust + 1;

            System.out.println("Scans reveal coherent geometry etched into the hull—like an equation carved in spacetime.");
        } else if (choice2 == 3) {
            System.out.println("\n\"Lock tractor beam. Bring her to a stable pocket of orbit.\"");
            System.out.println("Effect: -1 Time, +1 Crew Trust");
            TimeRemaining = TimeRemaining - 1;
            CrewTrust = CrewTrust + 1;

            // deterministic micro-risk without RNG
            int checksum = (ShipHealth + CrewTrust + TimeRemaining) % 5;
            if (checksum == 0) {
                System.out.println("Stress crack along Deck 7. Minor plating buckles.");
                System.out.println("Additional Effect: -1 Ship Health");
                ShipHealth = ShipHealth - 1;
            } else {
                System.out.println("Tow complete. The derelict settles without incident.");
            }
        } else {
            System.out.println("\nHesitation lets the derelict drift closer to Neptune’s edge.");
            System.out.println("Effect: -1 Time, -1 Crew Trust");
            TimeRemaining = TimeRemaining - 1;
            CrewTrust = CrewTrust - 1;
        }

        System.out.println("\nMission status — Ship: " + ShipHealth + " | Crew: " + CrewTrust + " | Time: " + TimeRemaining + "\n");
        if (checkGameOver(ShipHealth, CrewTrust, TimeRemaining) != 0) { input.close(); return; }

        //  SCENARIO 3: THE HANDSHAKE 
        System.out.println("\n=== SCENARIO 3: THE HANDSHAKE ===");
        System.out.println("The transmission’s math forms a handshake protocol. Completing it could invite a reply… or something worse.");
        System.out.println("\nYour options:");
        System.out.println("1) Complete the handshake now and transmit.");
        System.out.println("2) Isolate and decode offline first (safer, slower).");
        System.out.println("3) Quarantine the fragment and scrub signals (risk crew confidence).");
        System.out.print("> ");
        int choice3 = input.nextInt();

        if (choice3 == 1) {
            System.out.println("\nYou authorize completion. The array sings as numbers align.");
            System.out.println("Effect: -1 Time, +1 Crew Trust");
            TimeRemaining = TimeRemaining - 1;
            CrewTrust = CrewTrust + 1;

            DecodedHandshake = 1;
        } else if (choice3 == 2) {
            System.out.println("\nYou route the waveform to a sealed buffer. Data and Geordi dissect it.");
            System.out.println("Effect: -2 Time, +1 Crew Trust");
            TimeRemaining = TimeRemaining - 2;
            CrewTrust = CrewTrust + 1;

            DecodedHandshake = 1;
        } else if (choice3 == 3) {
            System.out.println("\n\"No further contact. Quarantine the fragment.\" The bridge grows quiet.");
            System.out.println("Effect: +1 Ship Health, -2 Crew Trust");
            ShipHealth = ShipHealth + 1;
            CrewTrust = CrewTrust - 2;

            DecodedHandshake = 0;
        } else {
            System.out.println("\nDelay erodes opportunity; the signal begins to decay.");
            System.out.println("Effect: -1 Time, -1 Crew Trust");
            TimeRemaining = TimeRemaining - 1;
            CrewTrust = CrewTrust - 1;
        }

        // Small bonus if you boarded earlier and now decode
        if (RecoveredFragment == 1 && DecodedHandshake == 1) {
            System.out.println("Using the recovered fragment, Data resolves a checksum—this wasn’t a lure; it’s a calibration key.");
            System.out.println("Effect: +1 Crew Trust");
            CrewTrust = CrewTrust + 1;
        }

        System.out.println("\nMission status — Ship: " + ShipHealth + " | Crew: " + CrewTrust + " | Time: " + TimeRemaining + "\n");
        if (checkGameOver(ShipHealth, CrewTrust, TimeRemaining) != 0) { input.close(); return; }

        //  SCENARIO 4: THE RIFT 
        System.out.println("\n=== SCENARIO 4: THE RIFT ===");
        System.out.println("Space puckers off the derelict’s bow: a rift yawns, thin as a whisper, pulling at ionized dust.");
        if (DecodedHandshake == 0) {
            System.out.println("Without the completed handshake, the rift is unstable and unreadable.");
        } else {
            System.out.println("Telemetry aligns with the handshake. A pattern answers from the far side.");
        }

        System.out.println("\nYour options:");
        System.out.println("1) Use the deflector to stabilize the rift (engineering risk).");
        System.out.println("2) Retreat to a safe distance and observe (safer, hurts morale).");
        System.out.println("3) Send an autonomous probe with the handshake key.");
        System.out.print("> ");
        int choice4 = input.nextInt();

        if (choice4 == 1) {
            System.out.println("\n\"Modulate the main deflector. Follow the harmonic envelope.\"");
            System.out.println("Effect: -1 Ship Health, -1 Time, +1 Crew Trust");
            ShipHealth = ShipHealth - 1;
            TimeRemaining = TimeRemaining - 1;
            CrewTrust = CrewTrust + 1;

            if (DecodedHandshake == 1) {
                EntityBenevolent = 1; // ordered response
                System.out.println("The rift resonates, settling into a crystalline symmetry.");
            } else {
                EntityBenevolent = 0;
                System.out.println("Without the key, the rift fights the modulation and lashes out.");
                System.out.println("Additional backlash scorches the forward shields. Effect: -1 Ship Health");
                ShipHealth = ShipHealth - 1;
            }
        } else if (choice4 == 2) {
            System.out.println("\n\"Take us back. Keep a sensor lock.\" The crew exhales—some in relief, others in disappointment.");
            System.out.println("Effect: +1 Ship Health, -1 Crew Trust");
            ShipHealth = ShipHealth + 1;
            CrewTrust = CrewTrust - 1;

            EntityBenevolent = 0; // no engagement, no proof of intent
        } else if (choice4 == 3) {
            System.out.println("\nA probe darts forward, broadcasting the completed handshake on a tight beam.");
            System.out.println("Effect: -1 Time, +1 Crew Trust");
            TimeRemaining = TimeRemaining - 1;
            CrewTrust = CrewTrust + 1;

            if (DecodedHandshake == 1) {
                EntityBenevolent = 1;
                System.out.println("Probe returns structured data: not a weapon—an invitation.");
            } else {
                EntityBenevolent = 0;
                System.out.println("Probe telemetry garbles; the rift spasms and the link severs.");
            }
        } else {
            System.out.println("\nThe rift widens a fraction while the bridge waits for your word.");
            System.out.println("Effect: -1 Time, -1 Crew Trust");
            TimeRemaining = TimeRemaining - 1;
            CrewTrust = CrewTrust - 1;
            EntityBenevolent = 0;
        }

        System.out.println("\nMission status — Ship: " + ShipHealth + " | Crew: " + CrewTrust + " | Time: " + TimeRemaining + "\n");
        if (checkGameOver(ShipHealth, CrewTrust, TimeRemaining) != 0) { input.close(); return; }

        //  SCENARIO 5: THE CHOICE 
        System.out.println("\n=== SCENARIO 5: THE CHOICE ===");
        System.out.println("The rift steadies—barely. You stand at the threshold of either first contact or containment.");
        System.out.println("\nYour options:");
        System.out.println("1) Attempt peaceful contact through the rift.");
        System.out.println("2) Seal the rift permanently with a deflector surge.");
        System.out.println("3) Sacrifice the derelict (or Horizon if needed) to collapse the rift and protect Neptune.");
        System.out.print("> ");
        int choice5 = input.nextInt();

        if (choice5 == 1) {
            if (DecodedHandshake == 1 && EntityBenevolent == 1 && TimeRemaining > 0 && CrewTrust > 0) {
                System.out.println("\nYou speak in math, then in meaning. The rift answers with harmonics like sung glass.");
                System.out.println("Effect: +1 Crew Trust");
                CrewTrust = CrewTrust + 1;
                System.out.println("A peaceful exchange establishes parameters for safe transit. History will remember this day.");
            } else {
                System.out.println("\nYour hail meets turbulence—either mistranslated or rebuffed.");
                System.out.println("Effect: -1 Time, -1 Crew Trust");
                TimeRemaining = TimeRemaining - 1;
                CrewTrust = CrewTrust - 1;
                if (TimeRemaining <= 0 || CrewTrust <= 0) { checkGameOver(ShipHealth, CrewTrust, TimeRemaining); input.close(); return; }
                System.out.println("With conditions deteriorating, you pivot to containment.");
                ShipHealth = ShipHealth - 1;
                System.out.println("Fallback Effect: -1 Ship Health");
            }
        } else if (choice5 == 2) {
            System.out.println("\n\"On my mark. All power to the deflector. Seal it.\"");
            int cost = 2;
            if (DecodedHandshake == 1) {
                cost = 1; // with the key, it's cheaper
            }
            ShipHealth = ShipHealth - cost;
            TimeRemaining = TimeRemaining - 1;
            System.out.println("Effect: -" + cost + " Ship Health, -1 Time");
            System.out.println("The rift buckles, then stitches shut. Space is quiet again.");
        } else if (choice5 == 3) {
            System.out.println("\nYou rig the derelict’s warp core (or Horizon’s in extremis) to generate a counter-harmonic implosion.");
            int sacrifice = 2;
            if (RecoveredFragment == 1) {
                sacrifice = 1; // fragment helps shape the blast
            }
            ShipHealth = ShipHealth - sacrifice;
            TimeRemaining = TimeRemaining - 1;
            CrewTrust = CrewTrust + 1; // decisive protection earns respect
            System.out.println("Effect: -" + sacrifice + " Ship Health, -1 Time, +1 Crew Trust");
            System.out.println("The rift collapses in a ring of ghost-light over Neptune.");
        } else {
            System.out.println("\nMoments bleed away. The rift destabilizes.");
            System.out.println("Effect: -1 Time, -1 Crew Trust");
            TimeRemaining = TimeRemaining - 1;
            CrewTrust = CrewTrust - 1;
        }

        System.out.println("\nMission status — Ship: " + ShipHealth + " | Crew: " + CrewTrust + " | Time: " + TimeRemaining + "\n");
        if (checkGameOver(ShipHealth, CrewTrust, TimeRemaining) != 0) { input.close(); return; }

        //  FINAL SUMMARY 
        System.out.println("\n==================================================");
        System.out.println("Final Mission Status — Ship: " + ShipHealth +
                           " | Crew Trust: " + CrewTrust +
                           " | Time: " + TimeRemaining);
        System.out.println("==================================================\n");
        epilogue(ShipHealth, CrewTrust, TimeRemaining, DecodedHandshake, EntityBenevolent);
    }

    // Helper: game-over checks
    // Returns an int code: 0 = continue, 1 = ship lost, 2 = mutiny, 3 = anomaly collapse
    static int checkGameOver(int ShipHealth, int CrewTrust, int TimeRemaining) {
        if (ShipHealth <= 0) {
            System.out.println("\nThe ship is destroyed!");
            System.out.println("\n                _.-^^---....,,--\n" +
                    "            _--                  --_\n" +
                    "           <          BOOM!          >\n" +
                    "            |   THE SHIP EXPLODES!   |\n" +
                    "             \\._                _./\n" +
                    "                '''--. . , ; .--'''\n" +
                    "                      | |   |\n" +
                    "                   .-=||  | |=-.\n" +
                    "                   `-=#$%&%$#=-'\n" +
                    "                        | ;  :|\n" +
                    "   ____                 | |   |\n" +
                    "  /####\\               /  .-.  \\\n" +
                    " |######|             /  (   )  \\\n" +
                    " |######|            /.-'     `-.\\ \n" +
                    "  \\####/            /             \\\n" +
                    "   \\__/           /                 \\\n" +
                    "      \\    \\_____/                   \\____/\n" +
                    "       \\________/                     \\___/\n" +
                    "          ***** THE SHIP IS LOST *****\n");
            return 1;
        }
        if (CrewTrust <= 0) {
            System.out.println("\nThe crew mutinies!");
            System.out.println("\n         ______________\n" +
                    "        /@@@@|     |###\\\n" +
                    "       /@@@@@|     |####\\\n" +
                    "      /@@@@@@|     |#####\\\n" +
                    "     |@@@@@@@@\\   /######|\n" +
                    "     |#########\\_/#######|\n" +
                    "      \\@@@@@@@@@@@@@@@@@/\n" +
                    "       \\###############/\n" +
                    "        `---.     .---'\n" +
                    "             |   |\n" +
                    "             |   |\n" +
                    "             |   |\n" +
                    "             |   |\n" +
                    "         _.-'     `-._\n" +
                    "        (   M U T I N Y! )\n" +
                    "         \\  The Crew Rises /\n" +
                    "          `-.__     __.-'\n" +
                    "                '''''\n" +
                    "     ***** THE CREW TAKES OVER *****\n");
            return 2;
        }
        if (TimeRemaining <= 0) {
            System.out.println("\nThe anomaly collapses!");
            System.out.println("\n                .-'````'-.\n" +
                    "             .-'          '-.\n" +
                    "           .'   .--.  .--.   '.\n" +
                    "          /   .'   )(   '.   \\\n" +
                    "         |   /  .-'  `-.  \\   |\n" +
                    "         |  |  (  ANOMALY ) |  |\n" +
                    "         |   \\   '-..-'   /   |\n" +
                    "          \\   '.        .'   /\n" +
                    "           '.   `'----'`   .'\n" +
                    "             '-.        .-'\n" +
                    "                '-....-'\n" +
                    "       Space bends... reality shatters...\n" +
                    "          THE ANOMALY COLLAPSES!\n" +
                    "     ***** TIME ITSELF DISINTEGRATES *****\n");
            return 3;
        }
        return 0; // continue game
    }

    //  Epilogue uses integer flags for different endings
    static void epilogue(int ShipHealth, int CrewTrust, int TimeRemaining,
                         int DecodedHandshake, int EntityBenevolent) {
        if (ShipHealth >= 5 && CrewTrust >= 6 && TimeRemaining >= 2 && DecodedHandshake == 1 && EntityBenevolent == 1) {
            System.out.println("EPILOGUE: First Contact (Classical)");
            System.out.println("The Horizon’s logs become curriculum. A corridor of understanding opens beyond Neptune.");
        } else if (DecodedHandshake == 1 && EntityBenevolent == 0 && ShipHealth > 0) {
            System.out.println("EPILOGUE: The Lock and the Key");
            System.out.println("You sealed a door you learned to open. The Federation will debate that choice for years.");
        } else if (DecodedHandshake == 0 && ShipHealth > 0 && TimeRemaining > 0) {
            System.out.println("EPILOGUE: The Opportunity Missed");
            System.out.println("Neptune’s sky quiets. Somewhere, a patient voice waits for a reply that never came.");
        } else if (ShipHealth > 0 && TimeRemaining > 0 && CrewTrust > 0) {
            System.out.println("EPILOGUE: Through Hard Vacuum");
            System.out.println("Battered but intact, Horizon returns to spacedock with scars—and stories.");
        } else {
            System.out.println("EPILOGUE: Echoes Over Neptune");
            System.out.println("What remains is a whisper and a warning carved into the void.");
        }
    }
}
