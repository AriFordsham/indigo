package indigo.gameengine.subsystems

import indigo.gameengine.GameTime
import indigo.gameengine.scenegraph.Text
import org.scalatest.FunSpec

class SubSystemsRegisterSpec extends FunSpec {

  describe("The sub system register") {

    it("should allow you to add sub systems") {
      val r = SubSystemsRegister.empty.add(PointsTrackerExample(10), PointsTrackerExample(20))

      assert(r.registeredSubSystems.length == 2, "wrong number")
    }

    it("should allow you to update sub systems") {
      val r = SubSystemsRegister.empty.add(PointsTrackerExample(10), PointsTrackerExample(50))

      val reports = r.update(GameTime.zero)(PointsTrackerEvent.Add(10)).register.reports

      assert(reports.contains("Points: 20"))
      assert(reports.contains("Points: 60"))
    }

    it("should allow you to update sub systems and emit events") {
      val r = SubSystemsRegister.empty.add(PointsTrackerExample(10), PointsTrackerExample(50))

      val updated = r.update(GameTime.zero)(PointsTrackerEvent.LoseAll)

      assert(updated.register.reports == List("Points: 0", "Points: 0"))
      assert(updated.events == List(GameOver, GameOver))
    }

    it("should allow you to render sub systems") {
      val r = SubSystemsRegister.empty.add(PointsTrackerExample(10), PointsTrackerExample(50))

      val rendered =
        r.update(GameTime.zero)(PointsTrackerEvent.Add(10))
          .register
          .render(GameTime.zero)
          .gameLayer
          .map(_.asInstanceOf[Text].text)

      assert(rendered.contains("20"))
      assert(rendered.contains("60"))
    }

  }

}
