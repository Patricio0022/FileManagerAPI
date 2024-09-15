import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"

export function InputButton() {
  return (
    <div className="flex w-full max-w-sm items-center space-x-2">
      <Input type="email" placeholder="Write your URL" />
      <Button type="submit">Submit</Button>
    </div>
  )
}
